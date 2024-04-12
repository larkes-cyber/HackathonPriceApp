from data.__all_models import *
from app.encrypt import encrypt
from app.EmailPhoneChecker import validate_login
from random import randrange


def query_user_level(user, session) -> UserLevel:
    return session.query(UserLevel).filter(UserLevel.id == user.type).first()


def query_user_by_id(user_id, session) -> User:
    return session.query(User).filter(User.id == user_id).first()


def query_all_users(session) -> [User]:
    return session.query(User).all()


def query_price_price(price_id, session) -> int:
    return session.query(PriceEntity).filter(PriceEntity.id == price_id).first().price


def is_email_exist(db_sess, email: str):
    return len(db_sess.query(User).filter(email == User.email).all()) == 1


def is_phone_exist(db_sess, phone: str):
    return len(db_sess.query(User).filter(phone == User.phone).all()) == 1


def is_user_exist(login, session):
    return is_phone_exist(session, login) or is_email_exist(session, login)


def get_user_by_login(login, session) -> User:
    # 1. Find user
    reg_type = validate_login(login)
    if reg_type == "email":
        # 2. Check if User exists
        user = session.query(User).filter(User.email == login).first()
        if user is None:
            raise Exception
    elif reg_type == "phone":
        # 2. Check if User exists
        user = session.query(User).filter(User.phone == login).first()
        if user is None:
            raise Exception
    else:
        # 2. Error
        raise Exception
    return user


class Auth:
    def __init__(self, secret):
        self.secret = secret

    def signup_user(self, login: str, password, session) -> User:
        print("------Registering user: ", login, "------", sep='')

        # 1. Validating Login
        reg_type = validate_login(login)
        if reg_type == "email":
            # 2. Check if User exists
            if is_email_exist(session, login):
                raise Exception

            # 3. Register him
            user = User(
                email=login,
                hashed_password=encrypt(password, self.secret),
                type=2
            )
        elif reg_type == "phone":
            # 2. Check if User exists
            if is_phone_exist(session, login):
                raise Exception

            # 3. Register him
            user = User(
                phone=login,
                hashed_password=encrypt(password, self.secret)
            )
        else:
            # 2. Error
            raise Exception

        # 4. Database commit
        session.add(user)
        session.commit()
        print("------User ", login, " registered successfully------", sep='')

        return user

    def login_user(self, login: str, password, session) -> User:

        try:
            user = get_user_by_login(login, session)
        except Exception:
            raise Exception

        # 3. Check password
        if encrypt(password, self.secret) != user.hashed_password:
            raise Exception

        return user


def commit_price(description: str, price: int, name: str, store:int, session) -> PriceEntity:
    new_price = PriceEntity(description=description, name=name, price=price, store=store)
    session.add(new_price)
    session.commit()
    return new_price


def query_all_prices(session) -> [PriceEntity]:
    return session.query(PriceEntity).all()


def query_price_by_id(price_id, session) -> PriceEntity:
    return session.query(PriceEntity).filter(PriceEntity.id==price_id).first()


def query_store_by_id(store_id, session) -> StoreEntity:
    return session.query(StoreEntity).filter(store_id==StoreEntity.id).first()


def commit_store(title, description, session) -> StoreEntity:
    new_store = StoreEntity(title=title, description=description)
    session.add(new_store)
    session.commit()
    return new_store


def query_all_stores(session) -> [StoreEntity]:
    return session.query(StoreEntity).all()


def query_price_img(price_id) -> str:
    return f"db/images/{price_id}.jpg"


def commit_price_image(store_id, session) -> str:
    img_id = randrange(10**9, 10**10)
    img_url = f"db/images/{img_id}.jpg"

    # SAVE IMAGEEEEEE FIXXXX PLEASE
    # SAVE IMAGEEEEEE FIXXXX PLEASE
    # SAVE IMAGEEEEEE FIXXXX PLEASE
    # SAVE IMAGEEEEEE FIXXXX PLEASE

    return img_url


def query_price_image_by_id(price_id, session) -> str:
    return f"db/images/{price_id}.jpg"


def query_store_prices(c_id, session) -> [PriceEntity]:
    return session.query(PriceEntity).filter(PriceEntity.store==c_id).all()