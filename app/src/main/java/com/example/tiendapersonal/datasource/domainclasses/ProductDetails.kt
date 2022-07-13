package com.example.tiendapersonal.datasource.domainclasses
import com.example.tiendapersonal.datasource.domainclasses.PersonalImage
import com.example.tiendapersonal.datasource.domainclasses.Result

class ProductDetails(
    id: String,
    mainImage: PersonalImage,
    name: String,
    installmentsTag: String,
    topTag: String,
    val legal: String,
    val images: Array<PersonalImage>
) : Result(id, mainImage, name, installmentsTag, topTag)