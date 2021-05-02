package me.ialext.staff.plugin.punishment;

import me.ialext.staff.api.punishment.Punishment;

import java.time.Duration;
import java.util.UUID;

public class PunishmentImpl implements Punishment {

  private final String id;
  private final UUID punisher;
  private final UUID punished;
  private final String reason;
  private final Duration duration;

  public PunishmentImpl(
      String id,
      UUID punisher,
      UUID punished,
      String reason,
      Duration duration
  ) {
    this.id = id;
    this.punisher = punisher;
    this.punished = punished;
    this.reason = reason;
    this.duration = duration;
  }

  @Override public String getId() {
    return id;
  }

  @Override public UUID getPunisher() {
    return punisher;
  }

  @Override public UUID getPunished() {
    return punished;
  }

  @Override public String getReason() {
    return reason;
  }

  @Override public Duration getDuration() {
    return duration;
  }
}
