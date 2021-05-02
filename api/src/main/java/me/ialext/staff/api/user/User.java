package me.ialext.staff.api.user;

import com.google.gson.annotations.SerializedName;
import me.ialext.staff.api.database.annotation.MongoCollectionKey;
import me.ialext.staff.api.model.SavableModel;
import me.ialext.staff.api.punishment.Punishment;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@MongoCollectionKey("users")
public interface User extends SavableModel {

  @SerializedName("activePunishments") List<Punishment> getActivePunishments();

  @SerializedName("pastPunishments") List<Punishment> getPastPunishments();

  default Player getPlayer() {
    return Bukkit.getPlayer(
        UUID.fromString(
            getId()
        )
    );
  }
}
