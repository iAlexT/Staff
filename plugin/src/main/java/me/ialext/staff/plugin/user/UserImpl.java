package me.ialext.staff.plugin.user;

import me.ialext.staff.api.punishment.Punishment;
import me.ialext.staff.api.user.User;

import java.util.List;

public class UserImpl implements User {

  private final String id;
  private final List<Punishment> activePunishments;
  private final List<Punishment> pastPunishments;

  public UserImpl(
      String id,
      List<Punishment> activePunishments,
      List<Punishment> pastPunishments
  ) {
    this.id = id;
    this.activePunishments = activePunishments;
    this.pastPunishments = pastPunishments;
  }

  @Override public String getId() {
    return id;
  }

  @Override public List<Punishment> getActivePunishments() {
    return activePunishments;
  }

  @Override public List<Punishment> getPastPunishments() {
    return pastPunishments;
  }
}
