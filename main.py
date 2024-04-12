import uvicorn


def main():
    uvicorn.run("app.api:app", host="0.0.0.0", port=8080, reload=True)


if __name__ == '__main__':
    main()
