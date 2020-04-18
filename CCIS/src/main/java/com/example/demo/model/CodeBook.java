package com.example.demo.model;
import org.omg.CORBA.portable.IDLEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "codeBook")
public class CodeBook {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="codeBook_id", unique = true, nullable = false)
   private Integer id;


   private HashMap<String, Collection<String>> codes;

   public CodeBook() {
   }

   public CodeBook(HashMap<String, Collection<String>> codes) {
      this.codes = codes;
   }

   public HashMap<String, Collection<String>> getCodes() {
      return codes;
   }

   public void setCodes(HashMap<String, Collection<String>> codes) {
      this.codes = codes;
   }
}