package by.arhor.psra.service;

public interface Service<T, K>
    extends CanFind<T, K>
          , CanDelete<K> {

//  implicit protected def convert[T, R](from: T, to: Class[R]): R = modelMapper.map[R](from, to)
//
//  implicit protected class DtoToEntity[T <: Dto](dto: T) {
//    def as[R <: Entity](implicit f: (T, Class[R]) => R, tag: ClassTag[R]): R = {
//      val targetClass = tag.runtimeClass.asInstanceOf[Class[R]]
//      f(dto, targetClass)
//    }
//  }
//
//  implicit protected class EntityToDto[T <: Entity](entity: T) {
//    def as[R <: Dto](implicit f: (T, Class[R]) => R, tag: ClassTag[R]): R = {
//      val targetClass = tag.runtimeClass.asInstanceOf[Class[R]]
//      f(entity, targetClass)
//    }
//  }
}
