from fastapi import APIRouter, Depends, status, UploadFile, File
from fastapi.responses import FileResponse
from fastapi.exceptions import HTTPException
from fastapi_jwt_auth2 import AuthJWT
import os
from fastapi.encoders import jsonable_encoder
from ai.image_scanner import scan_image

# DB session creator
from data import db_session
# DB queries
from db.db_requests import query_user_by_id, commit_price_image, query_price_img

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


@price_img_router.post('/upload_image/', status_code=status.HTTP_201_CREATED)
async def upload_price_image(file: UploadFile = File(...), Authorize: AuthJWT = Depends()):
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

    if user.is_spam:
        # SAVING
        try:
            img_id, img_path = commit_price_image(file.file)
        except Exception:
            return HTTPException(status_code=status.HTTP_406_NOT_ACCEPTABLE, detail="Ошибка при сохранении изображения")
        finally:
            file.file.close()
        # SCANNING
        response = {
            "id": img_id,
            "data": scan_image(img_path)
        }
        return jsonable_encoder(response)

    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED,
                         detail="Нет доступа"
                         )


@price_img_router.get('/{price_id}')
async def price_image(price_id: int):
    """
        ## List all price images
        This lists all prices.


    """
    """
            ## List price image


        """
    image = query_price_img(price_id)
    return jsonable_encoder(image)
