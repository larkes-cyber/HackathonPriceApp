import datetime
import time

from fastapi import APIRouter, status, Depends
from app.api_schemes import PassScheme
from fastapi.exceptions import HTTPException
from fastapi_jwt_auth2 import AuthJWT
from fastapi.encoders import jsonable_encoder
# env set up
from dotenv import load_dotenv
from os import environ as env

# DB session creator
from data import db_session
# DB queries
from db.db_requests import Auth, is_user_exist

auth_router = APIRouter(
    prefix='/api/auth',
    tags=['auth']

)

# env load up
load_dotenv()
API_SECRET = env['API_SECRET']
SALT = env['SALT']
exec(f"SALT = {SALT}")

UserManager = Auth(SALT)


@auth_router.get('/')
async def hello(Authorize: AuthJWT = Depends()):
    """
        ## Sample hello world route

    """
    try:
        Authorize.jwt_required()

    except Exception as e:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                            detail="Invalid Token")

    return {"message": "Hello World"}


@auth_router.post('/signup', status_code=status.HTTP_201_CREATED
                  )
async def signup(user: PassScheme):
    """
        ## Create a user
        This requires the following
        ```
                login:int
                password:str
        ```

    """

    session = db_session.create_session()

    if is_user_exist(user.login, session):
        raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST,
                             detail="Пользователь с таким логином уже существует"
                             )
    try:
        new_user = UserManager.signup_user(user.login, user.password, session)
    except Exception:
        raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST,
                             detail="Логин должен быть телефоном или email"
                             )

    session.add(new_user)
    session.commit()

    response = {"login": user.login, "password": user.password}

    return response


# login route

@auth_router.post('/login', status_code=200)
async def login(pass_data: PassScheme, Authorize: AuthJWT = Depends()):
    """
        ## Login a user
        This requires
            ```
                username:str
                password:str
            ```
        and returns a token pair `access` and `refresh`
    """
    session = db_session.create_session()

    try:
        user = UserManager.login_user(pass_data.login, pass_data.password, session)
        access_token = Authorize.create_access_token(subject=user.id, expires_time=datetime.timedelta(days=1))
        refresh_token = Authorize.create_refresh_token(subject=user.id, expires_time=datetime.timedelta(days=1))

        response = {
            "access": access_token,
            "refresh": refresh_token
        }

        return jsonable_encoder(response)

    except Exception:
        raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST,
                             detail="Неправильный Логин или Пароль"
                             )


# refreshing tokens

@auth_router.get('/refresh')
async def refresh(Authorize: AuthJWT = Depends()):
    """
    ## Create a fresh token
    This creates a fresh token. It requires an refresh token.
    """

    try:
        Authorize.jwt_refresh_token_required()

    except Exception as e:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                             detail="Please provide a valid refresh token"
                             )

    current_user = Authorize.get_jwt_subject()

    access_token = Authorize.create_access_token(subject=current_user)

    return access_token
