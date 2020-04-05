package model;
import java.util.*;

public class Request {
   public enum RequestType{};
   private RequestType type;
   private String description;

   public Request() {
   }

   public Request(RequestType type, String description) {
      this.type = type;
      this.description = description;
   }

   public RequestType getType() {
      return type;
   }

   public void setType(RequestType type) {
      this.type = type;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}