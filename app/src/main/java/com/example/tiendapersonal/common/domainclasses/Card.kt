package com.example.tiendapersonal.common.domainclasses

import com.example.tiendapersonal.datasource.domainclasses.PersonalImage
import com.example.tiendapersonal.datasource.domainclasses.Result

class Card(val id: String, val mainImage: PersonalImage, val name: String, val installmentsTag: String?, val topTag: String?){
    constructor(result: Result) : this(result.id, result.mainImage, result.name,result.installmentsTag, result.topTag)
}