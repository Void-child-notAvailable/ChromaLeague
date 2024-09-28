package net.booone.chromaleague.rest.eventdata;

import net.booone.chromaleague.state.RunningState;

import java.util.Objects;

public enum EventType {
    UNSUPPORTED,
    ALLY_BARON_KILL,
    ALLY_HERALD_KILL,
    ALLY_CHEMTECH_DRAGON_KILL,
    ALLY_CLOUD_DRAGON_KILL,
    ALLY_ELDER_DRAGON_KILL,
    ALLY_HEXTECH_DRAGON_KILL,
    ALLY_INFERNAL_DRAGON_KILL,
    ALLY_MOUNTAIN_DRAGON_KILL,
    ALLY_OCEAN_DRAGON_KILL,
    ALLY_GRUBS_KILL,
    ENEMY_GRUBS_KILL,
    ENEMY_BARON_KILL,
    ENEMY_HERALD_KILL,
    ENEMY_CHEMTECH_DRAGON_KILL,
    ENEMY_CLOUD_DRAGON_KILL,
    ENEMY_ELDER_DRAGON_KILL,
    ENEMY_HEXTECH_DRAGON_KILL,
    ENEMY_INFERNAL_DRAGON_KILL,
    ENEMY_MOUNTAIN_DRAGON_KILL,
    ENEMY_OCEAN_DRAGON_KILL,
    GAME_START,
    GAME_END_VICTORY,
    GAME_END_DEFEAT,
    ACTIVE_PLAYER_DIED,
    ACTIVE_PLAYER_KILL,
    ACTIVE_PLAYER_ASSIST;

    public static EventType fromEvent(Event event) {
        if (event != null) {
            System.out.println(event.EventName());
            if ("GameStart".equals(event.EventName())) {
                return GAME_START;
            }
            if ("ChampionKill".equals(event.EventName())) {
                final String riotIdGameName = RunningState.getGameState().getActivePlayer().riotIdGameName();
                if (riotIdGameName.equals(event.VictimName())) {
                    return ACTIVE_PLAYER_DIED;
                } else if (riotIdGameName.equals(event.KillerName())) {
                    return ACTIVE_PLAYER_KILL;
                } else if (event.Assisters() != null && event.Assisters().contains(riotIdGameName)) {
                    return ACTIVE_PLAYER_ASSIST;
                }
            }

            if ("DragonKill".equals(event.EventName())) {
                return switch (Objects.requireNonNull(DragonType.fromApiType(event.DragonType()))) {
                    case CHEMTECH -> {
                        if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                            yield ALLY_CHEMTECH_DRAGON_KILL;
                        }
                        yield ENEMY_CHEMTECH_DRAGON_KILL;
                    }
                    case CLOUD -> {
                        if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                            yield ALLY_CLOUD_DRAGON_KILL;
                        }
                        yield ENEMY_CLOUD_DRAGON_KILL;
                    }
                    case ELDER -> {
                        if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                            yield ALLY_ELDER_DRAGON_KILL;
                        }
                        yield ENEMY_ELDER_DRAGON_KILL;
                    }
                    case HEXTECH -> {
                        if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                            yield ALLY_HEXTECH_DRAGON_KILL;
                        }
                        yield ENEMY_HEXTECH_DRAGON_KILL;
                    }
                    case INFERNAL -> {
                        if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                            yield ALLY_INFERNAL_DRAGON_KILL;
                        }
                        yield ENEMY_INFERNAL_DRAGON_KILL;
                    }
                    case OCEAN -> {
                        if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                            yield ALLY_OCEAN_DRAGON_KILL;
                        }
                        yield ENEMY_OCEAN_DRAGON_KILL;
                    }
                    case MOUNTAIN -> {
                        if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                            yield ALLY_MOUNTAIN_DRAGON_KILL;
                        }
                        yield ENEMY_MOUNTAIN_DRAGON_KILL;
                    }
                };
            }

            if("HordeKill".equals(event.EventName())){
                if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                    return ALLY_GRUBS_KILL;
                }
                return ENEMY_GRUBS_KILL;
            }

            if ("BaronKill".equals(event.EventName())) {
                if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                    return ALLY_BARON_KILL;
                }
                return ENEMY_BARON_KILL;
            }

            if ("HeraldKill".equals(event.EventName())) {
                if (RunningState.getGameState().getPlayerList().isAlly(event.KillerName())) {
                    return ALLY_HERALD_KILL;
                }
                return ENEMY_HERALD_KILL;
            }

            if ("GameEnd".equals(event.EventName())) {
                if ("Win".equals(event.Result())) {
                    return GAME_END_VICTORY;
                }
                return GAME_END_DEFEAT;
            }
        }
        return UNSUPPORTED;
    }
}
