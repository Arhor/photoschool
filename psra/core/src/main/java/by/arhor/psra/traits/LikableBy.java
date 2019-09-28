package by.arhor.psra.traits;

public interface LikableBy<WHO> {

  int getLikes();

  void like(WHO lover);

  void dislike(WHO hater);

}
