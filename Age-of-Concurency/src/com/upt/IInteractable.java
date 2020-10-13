package com.upt;

public interface IInteractable
{
    enum ActionType { gather, upgrade, repair, train}

    void interact(Hero hero, ActionType actionType);
}
