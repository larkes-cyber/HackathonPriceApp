import json

import requests
from requests.structures import CaseInsensitiveDict

url2 = "http://localhost:8080/api/auth/signup"


headers = CaseInsensitiveDict()
headers["Accept"] = "application/json"
headers["Content-Type"] = "application/json"

# data = '{"login": "79851173554", "password": "kasdjhvsauhewiviweb213"}'
# resp = requests.post(url2, data=data, headers=headers)
# print(resp.status_code, resp.content)
# headers["bearer"] = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlhdCI6MTcwNjM2NzM5MiwibmJmIjoxNzA2MzY3MzkyLCJqdGkiOiJhYWU0YjNkMC04YWVlLTQzMGItOTg3Ni1mMmUzM2MxMzkwZmMiLCJleHAiOjE3MDYzNjgyOTIsInR5cGUiOiJhY2Nlc3MiLCJmcmVzaCI6ZmFsc2V9.qiG-dNegeYbjt7LOM3e4QUkWZMtPIuYYD-W2sjzDG4I"
# headers["refresh"] = ("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlhdCI6MTcwNjM2NzM5MiwibmJmIjoxNzA2MzY3MzkyLCJqdGkiOiIxYTg2Y2FiMC03YWI0LTQ1MzktODUzYy1mMDNiNzk5YjhmNWYiLCJleHAiOjE3MDg5NTkzOTIsInR5cGUiOiJyZWZyZXNoIn0.p3MUupFtHzOT1uRhG4XSaE2BxAyLEjqpha7l4E9o7aY")


for i in range(79851173556, 79851173574):
    data = '{"login": "'+str(i)+'", "password": "kasdjhvsauhewiviweb213"}'
    resp = requests.post("http://localhost:8080/api/auth/login", data=data, headers=headers)
    print(resp.status_code, resp.content, resp)
    # print(json.loads(resp.text))
    a = json.loads(resp.content)["access"]
    print(a)

    headers["Authorization"] = f'Bearer {a}'
    #
    #
    # data = '{"login": "79851173554", "password": "kasdjhvsauhewiviweb213"}'
    resp = requests.post("http://localhost:8080/api/orders/place_order", data='{"content": {}, "address": ""}', headers=headers)


print(resp.status_code, resp.content)
# resp = requests.post(url, data='{"content":{}}', headers=headers)
# print(resp.status_code, resp.content)