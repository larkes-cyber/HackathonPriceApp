from fastapi import APIRouter, Depends, status
from fastapi.exceptions import HTTPException
from fastapi_jwt_auth2 import AuthJWT
from app.api_schemes import UserLevelScheme
# from schemas import OrderModel, OrderStatusModel
from fastapi.encoders import jsonable_encoder

# DB session creator
from data import db_session
# DB queries
from db.db_requests import (query_user_by_id, query_user_level, query_all_users)
user_router = APIRouter(
    prefix="/api/users",
    tags=['users']
)


@user_router.get('/')
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


@user_router.patch("/user/update_level/{user_id}")
async def update_user_level(user_id: int, data: UserLevelScheme, Authorize: AuthJWT = Depends()):
    """
            ## Updating User Level
            This requires the following
            - user_level : int
        """

    try:
        Authorize.jwt_required()

    except Exception as e:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid Token")

    current_user = Authorize.get_jwt_subject()

    session = db_session.create_session()

    user = query_user_by_id(current_user, session)

    if query_user_level(user, session).can_edit_level_user:
        user_to_update = query_user_by_id(user_id, session)

        user_to_update.is_admin = data.user_level

        session.commit()

        response = {"user_id": user_to_update.id,
                    "admin": data.user_level,
                    }
        return jsonable_encoder(response)

    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                         detail="Нет прав на промоут пользователей"
                         )



@user_router.get('/users/{user_id}')
async def get_user_by_id(user_id: int, Authorize: AuthJWT = Depends()):
    """
        ## Get a User by its ID
        This gets a user by its ID and is only accessed by a superuser


    """

    try:
        Authorize.jwt_required()
    except Exception as e:
        return HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Invalid Token"
        )

    current_user = Authorize.get_jwt_subject()

    session = db_session.create_session()

    user = query_user_by_id(current_user, session)

    if query_user_level(user, session).can_view_users:
        u = query_user_by_id(user_id, session)
        u = {"id": u.id, "level": query_user_level(u, session).title, "type": u.type, "phone": u.phone, "email": u.email,
         "join_date": u.join_date}
        return jsonable_encoder(u)

    return HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Нет доступа к пользователям"
    )


@user_router.get('/users')
async def list_all_users(Authorize: AuthJWT = Depends()):
    """
        ## Get a User by its ID
        This gets a user by its ID and is only accessed by a superuser


    """

    try:
        Authorize.jwt_required()
    except Exception as e:
        return HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Invalid Token"
        )

    current_user = Authorize.get_jwt_subject()

    session = db_session.create_session()

    user = query_user_by_id(current_user, session)

    if query_user_level(user, session).can_view_users:
        users = query_all_users(session)
        users = list(map(lambda u: {"id": u.id, "type": query_user_level(u, session).title, "phone": u.phone, "email": u.email, "join_date": u.join_date}, users))
        return jsonable_encoder(users)

    return HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Нет доступа к пользователям"
    )


