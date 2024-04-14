import cv2
import math

import nltk
from nltk.corpus import stopwords
from pymystem3 import Mystem
from string import punctuation
import pickle

import easyocr
from ultralytics import YOLO

reader = easyocr.Reader(["ru", "en"])
modelPriceTag = YOLO('models/detectPriceTag.pt')
modelPriceNameSocialPrice = YOLO('models/detectPriceNameSocialPrice.pt')
modelClassificationTitle = pickle.load(open("models/ClassificationTitle.pickle", 'rb'))
ClassificationTitleTfidfVectorizer = pickle.load(open("models/ClassificationTitleTfidfVectorizer.pickle", 'rb'))

nltk.download('stopwords')
mystem = Mystem()
russian_stopwords = stopwords.words("russian")


def preprocess_text(text):
    text = str(text)
    tokens = mystem.lemmatize(text.lower())
    tokens = [token for token in tokens if token not in russian_stopwords \
              and token != " " \
              and len(token) >= 3 \
              and token.strip() not in punctuation \
              and token.isdigit() == False]
    text = " ".join(tokens)
    return text


def convert_to_int(string):
    if '.' not in string:
        digits = ''
        for char in string:
            if char.isdigit():  # Проверяем, является ли символ цифрой
                digits += char
        if digits:  # Если строка не пуста
            return int(digits)
        else:
            return None  # Можно вернуть None, если в строке нет цифр
    else:
        return None


def detectPriceTag(pathFile):
    model = YOLO('models/detectPriceTag.pt')
    model.fuse()

    frame = cv2.imread(pathFile)

    results = model.track(frame, iou=0.4, conf=0.5, persist=True, imgsz=608, verbose=False,
                          tracker="models/botsort.yaml")
    praiceTags = list()

    if results[0].boxes.id != None:
        boxes = results[0].boxes.xyxy.cpu().numpy().astype(int)
        ids = results[0].boxes.id.cpu().numpy().astype(int)

        for box, idd in zip(boxes, ids):
            cropped = frame[box[1]:box[3], box[0]:box[2]]
            praiceTags.append(cropped)

    return praiceTags


def detectPriceNameSocialPrice(pathFile):
    model = YOLO('models/detectPriceNameSocialPrice.pt')
    model.fuse()

    priceTag = detectPriceTag(pathFile)
    # if len(priceTag) == 0:
    #     return Ошибка
    frame = priceTag[math.floor((len(priceTag) / 2))]

    results = model.track(frame, iou=0.4, conf=0.5, persist=True, imgsz=608, verbose=False,
                          tracker="models/botsort.yaml")
    title = ""
    category = ""
    price = ""
    socialPrice = ""
    flag = False

    if results[0].boxes.id != None:
        boxes = results[0].boxes.xyxy.cpu().numpy().astype(int)
        ids = results[0].boxes.id.cpu().numpy().astype(int)

        for box, idx in zip(boxes, ids):
            cropped = frame[box[1]:box[3], box[0]:box[2]]

            if idx == 2:
                title += "".join(reader.readtext(cropped, detail=0, paragraph=True))
            elif idx == 1:
                flag_price = True
                readerr = reader.readtext(cropped, detail=0)
                for item in readerr:
                    if convert_to_int(item) != None and flag_price:
                        price = f'{convert_to_int(item)}'
                        flag_price = False
                    elif convert_to_int(item) != None and (not (flag_price)):
                        price = price + "." + f'{convert_to_int(item)}'
            elif idx == 3:
                socialPrice += "".join(reader.readtext(cropped, detail=0, paragraph=True))

    new_category = [preprocess_text(title)]  # List of new titles
    new_category_tfidf = ClassificationTitleTfidfVectorizer.transform(new_category)
    predictions = modelClassificationTitle.predict(new_category_tfidf)

    if (len(price.split(".")) < 3) and (len(price) != 0) and (float(price) < 1):
        dataPrice = price.split(".")
        price = dataPrice[1] + "." + dataPrice[0]

    if socialPrice is not None:
        flag == True

    return {
        "title": title,
        "category": predictions[0],
        "price": price,
        "socialPrice": flag,
    }
