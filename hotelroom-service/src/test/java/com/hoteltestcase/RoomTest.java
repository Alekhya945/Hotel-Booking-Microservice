package com.hoteltestcase;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelroom.dao.HotelDao;
import com.hotelroom.dao.RoomDetailsDao;
import com.hotelroom.entity.Hotel;
import com.hotelroom.entity.RoomDetails;
import com.hotelroom.payload.RoomDetailsPayload;
import com.hotelroom.service.RoomDetailsServiceImpl;

@SpringBootTest
public class RoomTest {

	@InjectMocks
	private RoomDetailsServiceImpl roomDetailService;

	@Mock
	private RoomDetailsDao roomDetailsRepository;

	@Mock
	private HotelDao hotelRepository;

	@Test
	public void testCreateRoomDetails() {
		// Create sample data
		Hotel hotel = new Hotel();

		hotel.setHotelId(1);
		hotel.setHotelName("TheParkHotel");
		hotel.setAddress("Dr NTR Road");
		hotel.setDescription("A park is an area of natural");
		hotel.setCity("Visakhapatnam");
		hotel.setAvgRatePerDay(2500);
		hotel.setEmail("theparkhotel@gmail.com");
		hotel.setPhone1("9632588741");
		hotel.setPhone2("7895641232");
		hotel.setWebsite("www.theparkhotel.com");

		RoomDetailsPayload room1 = new RoomDetailsPayload();

		room1.setRoomId(101);
		
		room1.setRoomNo("201");
		room1.setRoomType("Ac");
		room1.setRatePerDay(3000);
		room1.setAvailable(true);
		hotel.setHotelId(1);

		RoomDetails room = new RoomDetails();
		room.setRoomId(101);
		room.setRoomNo("201");
		room.setRoomType("Ac");
		room.setRatePerDay(3000);
		room.setAvailable(true);
		hotel.setHotelId(101);

		when(roomDetailsRepository.save(room)).thenReturn(room);

		when(hotelRepository.findById(101)).thenReturn(Optional.of(hotel));

		RoomDetails createdRoom = roomDetailService.createRoomDetails(room1);

		verify(roomDetailsRepository, times(1)).save(room);
//		assertNotNull(createdRoom);
//		assertEquals(room.getRoomId(), createdRoom.getRoomId());
//		assertEquals(room.getRoomType(), createdRoom.getRoomType());

	}

	@Test
	public void testDeleteRoom() {
		// Sample data
		Hotel hotel = new Hotel();
		hotel.setHotelId(101);

		RoomDetailsPayload roomModel = new RoomDetailsPayload();
		roomModel.setRoomId(101);
	

		RoomDetailsPayload room = new RoomDetailsPayload();
		room.setRoomId(101);
		room.setHotel(hotel);

		// Mock behavior of repositories
		when(roomDetailsRepository.findById(101)).thenReturn(Optional.of(room));

		roomDetailService.deleteRoomDetails(101);
		verify(roomDetailsRepository, times(1)).deleteAll();
		


	}

	@Test
	public void testupdateRoom() {

		
		RoomDetails roomModel = new RoomDetails();
		roomModel.setRoomId(101);
		
		roomModel.setRoomType("Updated Room Type");
		roomModel.setRatePerDay(4000);

		RoomDetails room = new RoomDetails();
		room.setRoomId(101);
		room.setRoomType("Old Room Type");
		room.setRatePerDay(3000);
		
		when(roomDetailsRepository.findById(101)).thenReturn(Optional.of(room));
		when(roomDetailsRepository.save(room)).thenReturn(room);

		// Call the update method
		RoomDetailsPayload updatedRoom = roomDetailService(room);
		  verify(roomDetailsRepository).save(room);
		  Assertions.assertNotNull(updatedRoom);
		  Assertions.assertEquals(updatedRoom.getRoomType(), updatedRoom.getRoomType());
		  Assertions.assertEquals(updatedRoom.getRatePerDay(), updatedRoom.getRatePerDay());
	}
	
	 @Test
	    public void testGetRoomDetailsById() {
	        // Sample data
	        Hotel hotel = new Hotel();
	        hotel.setHotelId(101);

	        RoomDetails roomModel = new RoomDetails();
	        roomModel.setRoomId(101);
	     
	        roomModel.setRoomType("Room Type");
	        roomModel.setRatePerDay(4000);

	        RoomDetails room = new RoomDetails();
	        room.setRoomId(101);
	        room.setHotel(hotel);
	        room.setRoomType("Room Type");
	        room.setRatePerDay(4000);

	        // Mock behavior of repository
	        when(roomDetailsRepository.findById(101)).thenReturn(Optional.of(room));

	        // Call the method under test
	        RoomDetails retrievedRoomModel = roomDetailService.getRoomDetailsById(101);

	        // Verify that the room details are retrieved correctly
	        Assertions.assertEquals(retrievedRoomModel.getRoomId(), retrievedRoomModel.getRoomId());
	        Assertions.assertEquals(retrievedRoomModel.getRoomType(), retrievedRoomModel.getRoomType());
	        Assertions.assertEquals(retrievedRoomModel.getRatePerDay(), retrievedRoomModel.getRatePerDay());
	    }

	 @Test
	    public void testGetAllRoomDetails() {
	        // Sample data
	        Hotel hotel1 = new Hotel();
	        hotel1.setHotelId(101);

	        RoomDetails room = new RoomDetails();
	        room.setRoomId(101);
	        room.setHotel(hotel1);
	        room.setRoomType("Room Type 1");
	        room.setRatePerDay(4000);

	        Hotel hotel2 = new Hotel();
	        hotel2.setHotelId(102);

	        RoomDetails roomModel = new RoomDetails();
	        roomModel.setRoomId(102);
	        roomModel.setHotel(hotel2);
	        roomModel.setRoomType("Room Type 2");
	        roomModel.setRatePerDay(5000);

	        List<RoomDetails> rooms = new ArrayList<>();
	        rooms.add(room);
	        rooms.add(roomModel);

	        // Mock behavior of repository
	        when(roomDetailsRepository.findAll()).thenReturn(rooms);

	        // Call the method under test
	        List<RoomDetails> allRoomDetails = roomDetailService.getAllRoomDetails();

	        Assertions.assertEquals(2, allRoomDetails.size());
	 }
	      
	}

