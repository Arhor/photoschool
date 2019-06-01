package by.arhor.psra.repository.model

import by.arhor.psra.repository.model.traits.{Datable, Deletable}

abstract class Entity extends Serializable
                         with Datable
                         with Deletable
