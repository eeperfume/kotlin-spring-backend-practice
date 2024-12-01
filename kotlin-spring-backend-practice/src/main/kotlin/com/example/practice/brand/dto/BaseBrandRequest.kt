package com.example.practice.brand.dto

sealed class BaseBrandRequest(
    open val name: String,
    open val description: String?
)
// ├── dto
// │    ├── BaseBrandRequest.kt
// │    ├── CreateBrandRequest.kt
// │    ├── UpdateBrandRequest.kt
// │    └── BrandResponse.kt
// DTO를 상세히 분리한 이유:
//
// DTO를 상세히 분리한 이유는 각 DTO가 서로 다른 목적을 갖기 때문이다.
// 이를 통해 목적에 맞는 명확한 분리를 할 수 있으며, 코드의 가독성과 유지보수성을 높일 수 있다.
//
// 예를 들어, BaseBrandRequest, CreateBrandRequest, UpdateBrandRequest DTO의 경우, RESTful 설계 원칙에 따라 ID는 PathVariable을 통해 가져오기 때문에 요청 DTO에는 ID 필드가 필요하지 않다.
// 반면, 응답 DTO인 BrandResponse에서는 프론트엔드에서 ID를 활용할 수 있도록 ID 필드를 포함시켜야 할 수도 있다.
//
// 추가로 현재 BaseBrandRequest의 속성과 CreateBrandRequest, UpdateBrandRequest의 속성이 모두 일치하기 때문에 큰 차이를 느끼기 어려울 수 있다.
// 하지만, CreateBrandRequest에는 생성 일자(createdAt)나 UpdateBrandRequest에는 수정 일자(updatedAt)와 같은 특화된 속성이 필요할 수 있다.
//
// 이 경우, 공통 속성(name, description)은 BaseBrandRequest에 선언하고, 생성 및 수정에 특화된 속성은 각각 CreateBrandRequest와 UpdateBrandRequest에 선언하여
// 공통 정보는 상위 클래스에서 관리하고, 타입별로 달라지는 추가 정보는 하위 클래스에서 관리함으로써 중복을 줄일 수 있다.
