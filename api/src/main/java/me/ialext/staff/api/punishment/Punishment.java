package me.ialext.staff.api.punishment;

import com.google.gson.annotations.SerializedName;
import me.ialext.staff.api.model.SavableModel;

import java.time.Duration;
import java.util.UUID;

public interface Punishment extends SavableModel {

  @SerializedName("punisher") UUID getPunisher();

  @SerializedName("punished") UUID getPunished();

  @SerializedName("reason") String getReason();

  @SerializedName("duration") Duration getDuration();

}
