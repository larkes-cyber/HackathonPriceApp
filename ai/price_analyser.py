import random

from data.price import PriceEntity


def analyse_price(price, category, store, name):

    # SOME ANALYSE

    return {
        "res": "Test result description",
        "social": bool(random.randint(0,1)),
        "data":
            {
                "price": price,
                "category": category,
                "store": store,
                "name": name
            }
    }
