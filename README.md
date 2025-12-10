
# COMMANDS
## Vending Apparetes
http://localhost:8080/vending
POST {
	"geoLocation": "chikago",
    "model": "S",
    "summaryEarn": 110,
    "type": "CardPayment",
    "status": "WORKING",
    "serialNumber": 1041123132115130143131141111321,
    "invertNumber": 13103133311121134331331114213011,
    "companyCreater": "Sasmung",
    "dateCreated": "2024-01-01",
    "dateExploited": "2024-02-01",
    "dateLastCheck": "2025-06-09",
    "checkIntervalMonth": 6,
    "resourceHours": 1000,
    "dateInvertarization": "2024-04-02",
    "serviceInterval":"PT720H",
    "lastCheckWorkerId": 1
}

## Worker
http://localhost:8080/auth/sign-up
POST sign-up {
    "firstName": "iigore",
    "secondName": "bbabich",
    "thirdName": "Vladiimirovich",
    "email" : "1igore@email.com",
    "phone" : "81234567890",
    "role" : "ROLE_FRANCHAISER",
    "password": "123123Q"
}

http://localhost:8080/auth/sign-in
POST sign-in {
    "email": "1igore@email.com",
    "password": "123123Q"
}

http://localhost:8080/auth/refresh
POST refresh {
    "refreshToken" : "55ef6661-0d1b-4271-9861-e161d74dade8b"
}

## Sales
http://localhost:8080/Sales
GET http://localhost:8080/Sales/Last/5               -- получает продажи за нное количество дней

http://localhost:8080/Sales
POST {
    "vendingApparatId": 1,
    "productId": 1,
    "productsSaleCount": 30,
    "price": 0.52,
    "payMethod": "QR",
    "soldAt": "2025-12-08T01:38:29Z" 
}
