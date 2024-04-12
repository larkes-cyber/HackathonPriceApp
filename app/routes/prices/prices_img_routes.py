from fastapi import APIRouter, Depends, status, UploadFile, File
from fastapi.responses import FileResponse
from fastapi.exceptions import HTTPException
from fastapi_jwt_auth2 import AuthJWT
import os
from fastapi.encoders import jsonable_encoder

# DB session creator
from data import db_session
# DB queries
from db.db_requests import query_user_by_id, query_user_level, commit_price_image, query_price_img

price_img_router = APIRouter(
    prefix="/api/prices/images",
    tags=['prices', 'img']
)


@price_img_router.get('/')
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


@price_img_router.post('/upload_image/{price_id}', status_code=status.HTTP_201_CREATED)
async def upload_price_image(price_id: int, file: UploadFile = File(...), Authorize: AuthJWT = Depends()):
    """
        ## Placing  price
        This requires the following

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

        try:
            image_name = commit_price_image(price_id, session=session).image_name
            contents = file.file.read()
            with open(f'db/images/prices/{image_name}.png', 'wb') as f:
                f.write(contents)
            response = {"href": f"/prices/images/{image_name}"}
            file.file.close()
            return jsonable_encoder(response)
        except Exception:
            return HTTPException(status_code=status.HTTP_406_NOT_ACCEPTABLE, detail="Ошибка при сохранении изображения")
        finally:
            file.file.close()

    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                         detail="Вы не создатель коллекций"
                         )


# @price_img_router.get('/')
# async def list_all_prices_images():
#     """
#         ## List all price images
#         This lists all prices.
#
#
#     """
#     session = db_session.create_session()
#     prices = query_all_prices_images(session)
#
#     return jsonable_encoder(prices)


@price_img_router.get('/{price_id}')
async def list_price_images(price_id: int):
    """
        ## List all price images
        This lists all prices.


    """
    """
            ## List all price images
            This lists all prices.


        """
    session = db_session.create_session()
    image = query_price_img(price_id)

    return jsonable_encoder(image)

# @price_router.put('/price/update/{price_id}/')
# async def update_price(price_id: int, price: priceScheme, Authorize: AuthJWT = Depends()):
#     """
#         ## Updating a price
#         This requires the following
#         - title : str
#         - description : str
#     """
#
#     try:
#         Authorize.jwt_required()
#
#     except Exception as e:
#         raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid Token")
#
#     current_user = Authorize.get_jwt_subject()
#
#     session = db_session.create_session()
#
#     user = query_user_by_id(current_user, session)
#
#     if query_user_level(user, session).can_create_prices:
#
#         price_to_update = query_price_by_id(price_id, session)
#
#         price_to_update.title = price.name
#         price_to_update.description = price.description
#
#         session.commit()
#
#         response = {"title": price.title,
#                     "description": price.description,
#                     }
#         return jsonable_encoder(response)
#
#     return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
#                          detail="Вы не создатель Коллекций"
#                          )


@price_img_router.delete('/price/delete/{img_id}', status_code=status.HTTP_204_NO_CONTENT)
async def delete_price_img(img_id: int, Authorize: AuthJWT = Depends()):
    """
        ## Delete a price Image
        This deletes a price by its ID
    """

    try:
        Authorize.jwt_required()

    except Exception as e:
        return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid Token")

    current_user = Authorize.get_jwt_subject()

    session = db_session.create_session()
    user = query_user_by_id(current_user, session)

    if query_user_level(user, session).can_delete_prices:

        img_to_delete = query_price_img_by_id(img_id, session)

        if img_to_delete is None:
            return HTTPException(status_code=status.HTTP_400_BAD_REQUEST,
                                 detail="Изображение не существует"
                                 )

        session.delete(img_to_delete)

        try:
            os.remove(f"./db/images/prices/{img_id}.png")
        except Exception:
            return HTTPException(status_code=status.HTTP_400_BAD_REQUEST,
                                 detail="Ошибка"
                                 )

        session.commit()

        return img_to_delete

    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                         detail="Вы не можете удалить изображение"
                         )