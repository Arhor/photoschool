package by.arhor.psra.model

import by.arhor.psra.model.traits.{Datable, Deletable}

abstract class Entity extends Serializable
                         with Datable
                         with Deletable
