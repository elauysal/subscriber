# SWA Developer Prototype Application

Bu proje, Spring Boot kullanarak oluşturulmuş bir örnek uygulamadır. Uygulama, PostgreSQL veritabanı ve JSON dosyasından veri okuyarak çalışır. Hem REST hem de SOAP web servislerini içerir ve verileri in-memory cache üzerinde tutar.

## Özellikler
- Lütfen postgres dbsi olusturun tablolar kendiliğinden oluşacaktır. proje update ile yapılmıştır.
- JSON dosyasından veya PostgreSQL veritabanından veri okuma ve in-memory cache'e yükleme
- GET, POST, PUT ve DELETE işlemleri için RESTful API
- Tüm subscriber'ları ve belirli subscriber'ları SOAP web servisleri ile döndürme
- Verileri belirli aralıklarla güncelleme

## Gereksinimler

- Java 8 veya üzeri
- Maven 3.6.0 veya üzeri
- PostgreSQL veritabanı

## Kullanılan Teknolojiler

- Spring Boot
- Spring Data JPA
- Spring Web Services
- JAXB
- H2 Veritabanı (test için)
- Maven

## Kurulum

1. Projeyi klonlayın:
    ```bash
    git clone https://github.com/kullaniciadi/swa-developer-prototype.git
    cd swa-developer-prototype
    ```

2. `application.properties` dosyasını ayarlayın:
   `src/main/resources/application.properties` dosyasını açın ve PostgreSQL veritabanı ayarlarını yapın:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=123456
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Maven bağımlılıklarını indirin ve projeyi derleyin:
    ```bash
    mvn clean install
    ```

4. Uygulamayı çalıştırın:
    ```bash
    mvn spring-boot:run
    ```

5. JSON dosyasından verileri yüklemek için uygulama başladığında `src/main/resources/data.json` dosyasını kontrol edin.

## API Kullanımı

### REST Servisleri

#### Tüm Subscriber'ları Getir
- URL: `GET /api/subscribers/getAllSubscribers`
- Örnek Kullanım: `http://localhost:8080/api/subscribers/getAllSubscribers`

#### Aktif Subscriber'ları Getir
- URL: `GET /api/subscribers/getActiveSubscribers`
- Örnek Kullanım: `http://localhost:8080/api/subscribers/getActiveSubscribers`

#### ID ile Subscriber Getir
- URL: `GET /api/subscribers/getSubscriberById/{id}`
- Örnek Kullanım: `http://localhost:8080/api/subscribers/getSubscriberById/1`

#### Yeni Subscriber Oluştur
- URL: `POST /api/subscribers/subscriber`
- Body (JSON):
    ```json
    {
        "id": 4,
        "name": "Stephany Kirk",
        "msisdn": "905558887965",
        "status": "ACTIVE"
    }
    ```
- Örnek Kullanım: `http://localhost:8080/api/subscribers/subscriber`

#### Subscriber Güncelle
- URL: `PUT /api/subscribers/subscriber/{id}`
- Body (JSON):
    ```json
    {
        "id": 4,
        "name": "Stephany Kirk",
        "msisdn": "905558887965",
        "status": "ACTIVE"
    }
    ```
- Örnek Kullanım: `http://localhost:8080/api/subscribers/subscriber/4`

#### Subscriber Sil
- URL: `DELETE /api/subscribers/subscriber/{id}`
- Örnek Kullanım: `http://localhost:8080/api/subscribers/subscriber/4`

### SOAP Servisleri

SOAP servislerine `http://localhost:8080/ws/subscribers.wsdl` adresinden erişebilirsiniz.

#### Tüm Subscriber'ları Getir
- Request:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getAllSubscribersRequest/>
       </soapenv:Body>
    </soapenv:Envelope>
    ```
- Response:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getAllSubscribersResponse>
             <sub:subscriber>
                <sub:id>1</sub:id>
                <sub:name>Stephan King</sub:name>
                <sub:msisdn>905552551122</sub:msisdn>
                <sub:status>ACTIVE</sub:status>
             </sub:subscriber>
             <!-- Diğer subscriber'lar -->
          </sub:getAllSubscribersResponse>
       </soapenv:Body>
    </soapenv:Envelope>
    ```

#### Aktif Subscriber'ları Getir
- Request:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getActiveSubscribersRequest/>
       </soapenv:Body>
    </soapenv:Envelope>
    ```
- Response:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getActiveSubscribersResponse>
             <sub:subscriber>
                <sub:id>1</sub:id>
                <sub:name>Stephan King</sub:name>
                <sub:msisdn>905552551122</sub:msisdn>
                <sub:status>ACTIVE</sub:status>
             </sub:subscriber>
             <!-- Diğer aktif subscriber'lar -->
          </sub:getActiveSubscribersResponse>
       </soapenv:Body>
    </soapenv:Envelope>
    ```

#### ID ile Subscriber Getir
- Request:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getSubscriberByIdRequest>
             <sub:id>1</sub:id>
          </sub:getSubscriberByIdRequest>
       </soapenv:Body>
    </soapenv:Envelope>
    ```
- Response:
    ```xml
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sub="http://example.com/subscriber">
       <soapenv:Header/>
       <soapenv:Body>
          <sub:getSubscriberByIdResponse>
             <sub:subscriber>
                <sub:id>1</sub:id>
                <sub:name>Stephan King</sub:name>
                <sub:msisdn>905552551122</sub:msisdn>
                <sub:status>ACTIVE</sub:status>
             </sub:subscriber>
          </sub:getSubscriberByIdResponse>
       </soapenv:Body>
    </soapenv:Envelope>
    ```

## Loglama

Tüm API çağrıları ve SOAP istekleri `app.log` dosyasına loglanır. Loglar şu formatta tutulur:
