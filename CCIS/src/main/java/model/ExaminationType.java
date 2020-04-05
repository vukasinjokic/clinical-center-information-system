package model;
import java.util.*;

public class ExaminationType {
   private String name;
   private float duration;

   public ExaminationType() {
   }

   public ExaminationType(String name, float duration) {
      this.name = name;
      this.duration = duration;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public float getDuration() {
      return duration;
   }

   public void setDuration(float duration) {
      this.duration = duration;
   }
}