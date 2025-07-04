# Java Parking Lot Simulation 

A multithreaded Java-based simulation of a smart parking lot system. Each car is represented as a thread arriving from multiple gates and attempting to park in a limited number of parking spots.

## Overview

This desktop simulation models concurrent car arrivals and uses synchronization techniques to manage shared resources (parking spots). Key features include:
- Support for multiple gates (Gate 1, Gate 2, Gate 3)
- Semaphore-based control of parking slots
- Thread-safe logging of events
- Live simulation with delayed arrivals and duration-based parking
- Summary report showing number of cars served from each gate

##  Concepts Covered

- Java Threads
- Concurrency (Semaphore, synchronization)
- File-based input handling
- Singleton Design Pattern (Logger, ParkingLot)

##  Input Format

The simulation reads from a file named input.txt, where each line should follow this format:
```
Gate 2, Car 2, Arrival 3, Duration 2
Gate 1, Car 1, Arrival 0, Duration 4
```

- Gate X: The entry gate name.
- Car Y: Unique identifier.
- Arrival: Time in seconds before the car arrives.
- Duration: Time in seconds the car will stay parked.

##  How it works

Each Car is a thread:
- It sleeps until its arrival time.
- Tries to acquire a spot using a Semaphore.
- If no spots are available, the car waits.
- Once acquired, it sleeps for the parking duration.
- Upon exiting, the car frees its spot and logs the event.

##  Summary Report

At the end of the simulation, a report is printed with:
- Total cars served
- Cars served by each gate
- Occupancy history


