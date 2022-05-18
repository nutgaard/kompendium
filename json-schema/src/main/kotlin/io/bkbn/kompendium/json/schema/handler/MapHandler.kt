package io.bkbn.kompendium.json.schema.handler

import io.bkbn.kompendium.json.schema.SchemaGenerator
import io.bkbn.kompendium.json.schema.definition.JsonSchema
import io.bkbn.kompendium.json.schema.definition.MapDefinition
import kotlin.reflect.KClass
import kotlin.reflect.KType

object MapHandler {

  fun handle(type: KType): JsonSchema {
    require(type.arguments.first().type!!.classifier as KClass<*> == String::class) {
      "JSON requires that map keys MUST be Strings.  You provided ${type.arguments.first().type}"
    }
    val valueType = type.arguments[1].type!!
    val valueSchema = SchemaGenerator.fromType(valueType)
    return MapDefinition(valueSchema)
  }

}
