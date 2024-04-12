from fastapi import APIRouter, Depends, status
from fastapi.exceptions import HTTPException
from fastapi_jwt_auth2 import AuthJWT
from app.api_schemes import PriceScheme
# from schemas import OrderModel, OrderStatusModel
from fastapi.encoders import jsonable_encoder

# DB session creator
from data import db_session
# DB queries
from db.db_requests import query_user_by_id, query_user_level, commit_price, query_store_by_id, query_all_prices, \
    query_price_by_id, query_store_prices

price_router = APIRouter(
    prefix="/api/prices",
    tags=['prices']
)


@price_router.get('/')
async def hello(Authorize: AuthJWT = Depends()):
    """
        ## A sample hello world route
        This returns Hello world
    """

    try:
        Authorize.jwt_required()

    except Exception as e:
        return HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Invalid Token"
        )
    return {"message": "Hello World"}


@price_router.post('/place_price', status_code=status.HTTP_201_CREATED)
async def place_price(price: PriceScheme, Authorize: AuthJWT = Depends()):
    """
        ## Placing Price
        This requires the following
        - name : str
        - description : str
        - store : int
        - price : int

    """

    try:
        Authorize.jwt_required()

    except Exception as e:
        return HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Неправильный токен"
        )

    current_user = Authorize.get_jwt_subject()

    session = db_session.create_session()

    user = query_user_by_id(current_user, session=session)

    if query_user_level(user, session).can_create_price:
        commit_price(price.description, price.price, price.name, price.store, session=session)

        response = {"name": price.name,
                    "price": price.price,
                    "description": p.description,
                    "store": store.title if store is not None else ""
                    }

        return jsonable_encoder(response)

    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                         detail="Вы не можете загрузить ценник"
                         )


@price_router.get('/prices')
async def list_all_prices():
    """
        ## List all prices
        This lists all prices.
    """
    session = db_session.create_session()
    prices = query_all_prices(session)

    return jsonable_encoder(prices)


@price_router.get('/prices/{price_id}')
async def get_price_by_id(price_id: int):
    """
        ## Get price by its ID
    """

    session = db_session.create_session()
    price = query_price_by_id(price_id, session)

    if price is None:
        return HTTPException(status_code=status.HTTP_400_BAD_REQUEST,
                             detail="Данного ценника не существует"
                             )
    response = {"name": price.name,
                "price": price.price,
                "description": price.description,
                "store": price.store
                }

    return jsonable_encoder(response)


@price_router.get('/store/{store_id}')
async def get_store_prices_by_id(store_id: int):
    """
        ## Get an price by its ID
    """

    session = db_session.create_session()
    prices = query_store_prices(store_id, session)

    response = list(map(lambda price: {"id": price.id,
                                 "name": price.name,
                                 "price": price.price,
                                 "description": price.description,
                                 "store": price.store
                                 }, prices))

    return jsonable_encoder(response)


@price_router.put('/price/update/{price_id}/')
async def update_price(price_id: int, price: PriceScheme, Authorize: AuthJWT = Depends()):
    """
        ## Updating price
        This requires the following
        - name : str
        - description : str
        - store : int
        - price : int
    """

    try:
        Authorize.jwt_required()

    except Exception as e:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid Token")

    current_user = Authorize.get_jwt_subject()

    session = db_session.create_session()

    user = query_user_by_id(current_user, session)

    if query_user_level(user, session).can_create_price:
        price_to_update = query_price_by_id(price_id, session)

        price_to_update.name = price.name
        price_to_update.description = price.description
        price_to_update.price = price.price
        price_to_update.store = price.store
        session.commit()
        print(price_to_update)

        response = {"name": price.name,
                    "price": price.price,
                    "description": price.description,
                    "store": price.store
                    }
        return jsonable_encoder(response)

    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                         detail="Вы не создатель ценников"
                         )


@price_router.delete('/price/delete/{price_id}/', status_code=status.HTTP_204_NO_CONTENT)
async def delete_an_price(price_id: int, Authorize: AuthJWT = Depends()):
    """
        ## Delete price
        This deletes an order by its ID
    """

    try:
        Authorize.jwt_required()

    except Exception as e:
        return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid Token")

    current_user = Authorize.get_jwt_subject()

    session = db_session.create_session()

    user = query_user_by_id(current_user, session)

    if query_user_level(user, session).can_delete_price:

        price_to_delete = query_price_by_id(price_id, session)

        if price_to_delete is None:
            return HTTPException(status_code=status.HTTP_400_BAD_REQUEST,
                                 detail="Ценника не существует"
                                 )

        session.delete(price_to_delete)

        session.commit()

        return price_to_delete

    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                         detail="Вы не можете удалить ценник"
                         )
