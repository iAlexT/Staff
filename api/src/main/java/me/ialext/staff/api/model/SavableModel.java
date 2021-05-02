package me.ialext.staff.api.model;

import com.google.gson.annotations.SerializedName;

public interface SavableModel {

  @SerializedName("_id") String getId();

}
