package com.example.demo.controller;

import com.example.demo.dto.RestaurantDto;
import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/all")
    public ResponseEntity<Object> getRestaurant() {
        try{
            return  ResponseEntity.ok(restaurantService.getRestaurant());
        }
        catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/insert")
    public ResponseEntity<Object> insert(@RequestBody RestaurantDto restaurant){
        try{
        return ResponseEntity.ok(restaurantService.addRestaurant(restaurant));
        }
        catch (Exception ex){
            return new ResponseEntity<>("Not able to insert",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/delete/{Id}")
   public ResponseEntity<Object> delete(@PathVariable("Id") String Id){
        try{
            restaurantService.deleteRestaurant(Id);
            return ResponseEntity.ok("Sucessfully Deleted");
        }
        catch (Exception ex){
            return new ResponseEntity<>("Unable to delete",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update/{Id}")
    public ResponseEntity<Object>update(@PathVariable("Id") String Id,@RequestBody RestaurantDto restaurantDto){
        try {
            Restaurant updatedRestaurant = restaurantService.updateRestraunt(Id, restaurantDto);
            return ResponseEntity.ok(updatedRestaurant);
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
