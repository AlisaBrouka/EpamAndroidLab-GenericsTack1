package com.epam.impl;

public interface EdgeInterface <N extends Node> {

    N getStartPoint();
    N getTargetPoint();
    Integer getLength();
    Integer getWeight();

}
