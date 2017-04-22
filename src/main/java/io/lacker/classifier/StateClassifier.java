package io.lacker.classifier;

public interface StateClassifier<T> {

  boolean isAlive(T state);
}
