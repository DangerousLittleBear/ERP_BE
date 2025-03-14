# ERP 시스템 백엔드

이 프로젝트는 Spring Boot를 활용한 ERP(Enterprise Resource Planning) 시스템의 백엔드 애플리케이션입니다.

## 기능

- 회원(Member) 관리
- 수익(Revenue) 관리
- 판매(Sales) 관리
- 재고(Stock) 관리
- 창고(Warehouse) 관리
- JWT 기반 인증 및 권한 관리

## 기술 스택

- Java 17
- Spring Boot 3.2.3
- Spring Security
- Spring Data JPA
- H2 Database (개발용)
- JWT (JSON Web Token)
- Maven

## 시작하기

### 필수 조건

- Java 17 이상
- Maven

### 설치 및 실행

1. 프로젝트 클론
```bash
git clone https://github.com/yourusername/erp-backend.git
cd erp-backend
```

2. 애플리케이션 빌드
```bash
mvn clean install
```

3. 애플리케이션 실행
```bash
mvn spring-boot:run
```

## API 엔드포인트

### 인증

- `POST /api/auth/signin`: 로그인
- `POST /api/auth/signup`: 회원가입

### 회원 관리

- `GET /api/members`: 모든 회원 조회
- `GET /api/members/{id}`: 특정 회원 조회
- `PUT /api/members/{id}`: 회원 정보 수정
- `DELETE /api/members/{id}`: 회원 삭제

### 수익 관리

- `GET /api/revenues`: 모든 수익 조회
- `GET /api/revenues/{id}`: 특정 수익 조회
- `GET /api/revenues/date-range`: 날짜 범위로 수익 조회
- `GET /api/revenues/category/{category}`: 카테고리별 수익 조회
- `GET /api/revenues/source/{source}`: 출처별 수익 조회
- `POST /api/revenues`: 수익 등록
- `PUT /api/revenues/{id}`: 수익 정보 수정
- `DELETE /api/revenues/{id}`: 수익 삭제

### 판매 관리

- `GET /api/sales`: 모든 판매 조회
- `GET /api/sales/{id}`: 특정 판매 조회
- `GET /api/sales/order/{orderNumber}`: 주문번호로 판매 조회
- `GET /api/sales/date-range`: 날짜 범위로 판매 조회
- `GET /api/sales/customer/{customerName}`: 고객별 판매 조회
- `GET /api/sales/status/{status}`: 상태별 판매 조회
- `GET /api/sales/salesperson/{salesPersonId}`: 판매자별 판매 조회
- `POST /api/sales`: 판매 등록
- `PUT /api/sales/{id}`: 판매 정보 수정
- `PATCH /api/sales/{id}/status`: 판매 상태 수정
- `DELETE /api/sales/{id}`: 판매 삭제

### 재고 관리

- `GET /api/stocks`: 모든 재고 조회
- `GET /api/stocks/{id}`: 특정 재고 조회
- `GET /api/stocks/code/{productCode}`: 제품 코드로 재고 조회
- `GET /api/stocks/name/{productName}`: 제품명으로 재고 조회
- `GET /api/stocks/category/{category}`: 카테고리별 재고 조회
- `GET /api/stocks/warehouse/{warehouseId}`: 창고별 재고 조회
- `GET /api/stocks/low-stock`: 재고 부족 제품 조회
- `POST /api/stocks`: 재고 등록
- `PUT /api/stocks/{id}`: 재고 정보 수정
- `PATCH /api/stocks/{id}/quantity`: 재고 수량 수정
- `DELETE /api/stocks/{id}`: 재고 삭제

### 창고 관리

- `GET /api/warehouses`: 모든 창고 조회
- `GET /api/warehouses/active`: 활성화된 창고 조회
- `GET /api/warehouses/{id}`: 특정 창고 조회
- `GET /api/warehouses/code/{code}`: 코드로 창고 조회
- `GET /api/warehouses/name/{name}`: 이름으로 창고 조회
- `POST /api/warehouses`: 창고 등록
- `PUT /api/warehouses/{id}`: 창고 정보 수정
- `PATCH /api/warehouses/{id}/activate`: 창고 활성화
- `PATCH /api/warehouses/{id}/deactivate`: 창고 비활성화
- `DELETE /api/warehouses/{id}`: 창고 삭제

## 보안

이 애플리케이션은 JWT(JSON Web Token)를 사용하여 인증을 처리합니다. 모든 API 요청에는 Authorization 헤더에 Bearer 토큰이 포함되어야 합니다.

## 역할 및 권한

- `ROLE_ADMIN`: 모든 기능에 접근 가능
- `ROLE_MANAGER`: 대부분의 기능에 접근 가능, 일부 삭제 기능 제한
- `ROLE_USER`: 제한된 기능에만 접근 가능 