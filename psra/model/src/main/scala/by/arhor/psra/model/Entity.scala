package by.arhor.psra.model

import by.arhor.psra.model.traits.{Datable, Deletable, Identifiable}

abstract class Entity extends Serializable
                         with Identifiable
                         with Datable
                         with Deletable
