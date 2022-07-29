package com.traveler.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Room {

    String name;
    String desc;
    List<Item> items;
    String north;
    String south;
    String east;
    String west;
    static Room currentRoom;

    public static List<Room> allRooms;

    public static List<Room> roomsFromJsonToArray() throws IOException {
        Gson gson = new Gson();
        Type roomListType = new TypeToken<List<Room>>() {
        }.getType();
        Reader reader = new InputStreamReader(Room.class.getResourceAsStream("/rooms.json"));
        allRooms = new Gson().fromJson(reader, roomListType);
        reader.close();
        Room.currentRoom = allRooms.get(2);
        return allRooms;
    }

    // a method that returns current room info, aka toString
    public static void cmdRoomInfo() {
        System.out.println(currentRoom.toString());
    }

    // method cmdGo that changes the currentRoom to a corresponding room
    public void cmdGo(String noun) {
        switch (noun) {
            case "north":
                System.out.println("Attempting to go north\n");
                goNorth();
                break;
            case "south":
                System.out.println("Attempting to go south\n");
                goSouth();
                break;
            case "east":
                System.out.println("Attempting to go east\n");
                goEast();
                break;
            case "west":
                System.out.println("Attempting to go west\n");
                goWest();
                break;
        }
    }

    // TODO: a method that returns what they see in that direction
    public void cmdLook(String noun) {
        switch (noun) {
            case "north":
                System.out.println("Looking north, you see " + currentRoom.north);
                break;
            case "south":
                System.out.println("Looking south, you see " + currentRoom.south);
                break;
            case "east":
                System.out.println("Looking east, you see " + currentRoom.east);
                break;
            case "west":
                System.out.println("Looking west, you see " + currentRoom.west);
                break;
        }
    }

    // TODO: rename room to destination room, attempt to combine methods, (direction var included, with four if statements)
    public void goNorth() {
        for (Room room : allRooms) {
            if (room.name.equals(currentRoom.north)) {
                this.setCurrentRoom(room);
                return;
            }
        }
        System.out.println("Can't go North");
    }

    public void goSouth() {
        for (Room room : allRooms) {
            if (room.name.equals(currentRoom.south)) {
                this.setCurrentRoom(room);
                return;
            }
        }
        System.out.println("Can't go South");
    }

    public void goEast() {
        for (Room room : allRooms) {
            if (room.name.equals(currentRoom.east)) {
                this.setCurrentRoom(room);
                return;
            }
        }
        System.out.println("Can't go East");
    }

    public void goWest() {
        for (Room room : allRooms) {
            if (room.name.equals(currentRoom.west)) {
                this.setCurrentRoom(room);
                return;
            }
        }
        System.out.println("Can't go West");
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        System.out.println(currentRoom.toString());
    }

    @Override
    public String toString() {
        return "============================================\n" +
                "You are in the " + name + "\n" +
                desc + "\n" +
                "To the north is " + north + "\n" +
                "To the south is " + south + "\n" +
                "To the east is " + east + "\n" +
                "To the west is " + west + "\n";
    }


}