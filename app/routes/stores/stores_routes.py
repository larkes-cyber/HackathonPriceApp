from fastapi import APIRouter, Depends, status
from fastapi.exceptions import HTTPException
from fastapi_jwt_auth2 import AuthJWT
from app.api_schemes import PriceScheme, StoreScheme
# from schemas import OrderModel, OrderStatusModel
from fastapi.encoders import jsonable_encoder

# DB session creator
from data import db_session
# DB queries
from db.db_requests import query_user_by_id, query_user_level, commit_price, query_store_by_id, query_all_prices, \
    query_price_by_id, query_store_prices

store_router = APIRouter(
    prefix="/api/stores",
    tags=['stores']
)


@store_router.get('/')
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


@store_router.post('/create_store', status_code=status.HTTP_201_CREATED)
async def create_store(store: StoreScheme, Authorize: AuthJWT = Depends()):
    """
        ## Creating Store
        This requires the following
        - name : str
        - location : str
        - region : str
        - email : str

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
        commit_store(store.name, store.location, store.region, store.email, session=session)

        response = {
                    "name": store.name,
                    "location": store.location,
                    "region": store.region,
                    "email": store.email
                    }

        return jsonable_encoder(response)

    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                         detail="Вы не можете загрузить магазин"
                         )


@store_router.get('/stores')
async def list_all_stores():
    """
        ## List all stores
        This lists all stores.
    """
    session = db_session.create_session()
    stores = query_all_stores(session)

    return jsonable_encoder(stores)


@store_router.get('/stores/{store_id}')
async def get_store_by_id(store_id: int):
    """
        ## Get store by its ID
    """

    session = db_session.create_session()
    store = query_store_by_id(store_id, session)

    if store is None:
        return HTTPException(status_code=status.HTTP_400_BAD_REQUEST,
                             detail="Данного ценника не существует"
                             )
    response = {"name": store.name,
                "location": store.location,
                "region": store.region,
                "email": store.email
                }

    return jsonable_encoder(response)


@store_router.delete('/price/delete/{store_id}/', status_code=status.HTTP_204_NO_CONTENT)
async def delete_store(store_id: int, Authorize: AuthJWT = Depends()):
    """
        ## Delete store
        This deletes store by its ID
    """

    try:
        Authorize.jwt_required()

    except Exception as e:
        return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid Token")

    current_user = Authorize.get_jwt_subject()

    session = db_session.create_session()

    user = query_user_by_id(current_user, session)

    if query_user_level(user, session).can_delete_price:

        store_to_delete = query_price_by_id(store_id, session)

        if store_to_delete is None:
            return HTTPException(status_code=status.HTTP_400_BAD_REQUEST,
                                 detail="Магазина не существует"
                                 )

        session.delete(store_to_delete)

        session.commit()

        return store_to_delete

    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                         detail="Вы не можете удалить магазин"
                         )
