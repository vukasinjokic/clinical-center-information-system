package com.example.demo.model;
import org.omg.CORBA.portable.IDLEntity;

import javax.persistence.*;
import java.util.*;

/*
      Ova klasa predstavlja
      Mapa:   {kod_bolesti, dijagnoza(opis, ) uglavnom neka bude string }
*/

@Entity
@Table(name = "codeBook")
public class CodeBook {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id", unique = true, nullable = false)
   private Integer id;

   @ElementCollection
   @CollectionTable(name = "codes",
           joinColumns = {@JoinColumn(name = "code_book_id", referencedColumnName = "id")})
   @MapKeyColumn(name = "code")
   @Column(name = "description")
   private Map<String, String> codes;

   public CodeBook() {
   }

   public CodeBook(HashMap<String, String> codes) {
      this.codes = codes;
   }

   public Map<String, String> getCodes() {
      return codes;
   }

   public void setCodes(HashMap<String, String> codes) {
      this.codes = codes;
   }
}