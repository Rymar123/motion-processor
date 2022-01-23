package org.rymarski.motionprocessor.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthConstants {
  public static final String CAN_ADD_MOTION = "canAddMotion";
  public static final String CAN_UPDATE_MOTION = "canUpdateMotion";
  public static final String CAN_PROGRESS_MOTION = "canProgressMotion";
  public static final String CAN_DENY_MOTION = "canDenyMotion";
}
