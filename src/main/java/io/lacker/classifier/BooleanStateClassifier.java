package io.lacker.classifier;

public class BooleanStateClassifier implements StateClassifier<Boolean> {

  @Override
  public boolean isAlive(Boolean state) {
    return state;
  }
}
