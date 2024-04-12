from pydantic import BaseModel
import datetime

# env set up
from dotenv import load_dotenv
from os import environ as env

load_dotenv()
JWT_SECRET = env['JWT_SECRET']


def transform_date(date):
    return datetime.datetime.strptime(date, '%Y-%m-%d').date()


class OrderScheme(BaseModel):
    content: list
    address: str


class UserUpdateScheme(BaseModel):
    username: str
    phone: str
    mail: str
    password: str
    birth_date: str


class UserScheme(BaseModel):
    name: str
    password: str
    tel: str
    email: str
    is_admin: int


class PasswordUpdateScheme(BaseModel):
    old_password: str
    new_password: str


class PassScheme(BaseModel):
    login: str
    password: str


class IdScheme(BaseModel):
    obj_id: int


class PriceScheme(BaseModel):
    name: str
    category: str
    price: int
    store: int


class Settings(BaseModel):
    authjwt_secret_key: str = JWT_SECRET


class StoreScheme(BaseModel):
    name: str
    location: str
    region: str
    email: str


class UserLevelScheme(BaseModel):
    user_level: int
