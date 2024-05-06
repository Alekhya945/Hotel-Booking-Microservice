package com.hoteltestcase;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doNothing;
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
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelroom.dao.HotelDao;
import com.hotelroom.entity.Hotel;
import com.hotelroom.entity.RoomDetails;
import com.hotelroom.payload.RoomDetailsPayload;
import com.hotelroom.service.HotelServiceImpl;
import com.hotelroom.service.RoomDetailsService;
import com.hotelroom.service.RoomDetailsServiceImpl;

@SpringBootTest
public class HotelTest {
	

	@InjectMocks
	private HotelServiceImpl  hotelService;
	@InjectMocks
	private RoomDetailsServiceImpl roomDetailService;
	@Mock
	private HotelDao hotelRepository;
	@Mock
	private RoomDetailsService 	roomDetailsRepository;
	
	
	@Test
	public void testcreateHotel() {
		
		Hotel hotel =new Hotel();
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
		
		
		when(hotelRepository.save(hotel)).thenReturn(hotel);

        // Call the createHotel method
        Hotel createdHotel = hotelService.createHotel(hotel);
        verify(hotelRepository, times(1)).save(hotel);		
        Assertions.assertNotNull(createdHotel);
        Assertions.assertEquals(hotel.getHotelId(),createdHotel.getHotelId());
        Assertions.assertEquals(hotel.getHotelName(),createdHotel.getHotelName());
        Assertions.assertEquals(hotel.getEmail(),createdHotel.getEmail());
	}
	@Test
	public void testDeleteHotel() {
		
		Hotel hotel=new Hotel();
		
		hotel.setHotelId(2);
		hotel.setHotelName("Novtel");
		hotel.setAddress("RK Beach");
		hotel.setDescription("BeachView");
		hotel.setCity("Visakhapatnam");
		hotel.setAvgRatePerDay(4500);
		hotel.setEmail("Novtelhotel@gmail.com");
		hotel.setPhone1("8523697410");
		hotel.setPhone2("85693214701");
		hotel.setWebsite("www.Novtelhotel.com");
		
		
		when(hotelRepository.findById(10)).thenReturn(Optional.of(hotel));
		hotelService.deleteHotel(10);
		verify(hotelRepository,times(1)).delete(hotel);
	

		
	}
	@Test
	
	public void testGetHotelById() {
		
       Hotel hotel=new Hotel();
		
		hotel.setHotelId(3);
		hotel.setHotelName("HotelOceanVistBay");
		hotel.setAddress("Beach Road");
		hotel.setDescription("SuperLuxuious");
		hotel.setCity("Visakhapatnam");
		hotel.setAvgRatePerDay(3500);
		hotel.setEmail("hotelocean@gmail.com");
		hotel.setPhone1("8523697410");
		hotel.setPhone2("9854763210");
		hotel.setWebsite("www.hotelocean@gmail.com");

		when(hotelRepository.findById(101)).thenReturn(Optional.of(hotel));

		Hotel hotelList = hotelService.getHotelById(101);
		Assertions.assertEquals(101, hotelList.getHotelId());


		
		
		
		
	}
	
	@Test
	public void testGetAllHotel() {
		Hotel hotel=new Hotel();
		
		hotel.setHotelId(4);
		hotel.setHotelName("GreenPark");
		hotel.setAddress("RamNagar");
		hotel.setDescription("Green Park – It features 2 dining options, a fitness centre, rooftop swimming pool and rooms with free Wi-Fi");
		hotel.setCity("Visakhapatnam");
		hotel.setAvgRatePerDay(4500);
		hotel.setEmail("hotelgreenpark@gmail.com");
		hotel.setPhone1("7789944555");
		hotel.setPhone2("9988774455");
		hotel.setWebsite("www.hotelGreenpark@gmail.com");
		
		
        Hotel hotel1=new Hotel();
		
		hotel.setHotelId(5);
		hotel.setHotelName("Sunray Hotel");
		hotel.setAddress("Bhogapuram");
		hotel.setDescription("With a vision to offer the most relaxed holiday experiences, we, at");
		hotel.setCity("Visakhapatnam");
		hotel.setAvgRatePerDay(7500);
		hotel.setEmail("sunrayhotel@gmail.com");
		hotel.setPhone1("7412589630");
		hotel.setPhone2("8547963210");
		hotel.setWebsite("www.sunrayhotel.com");
		
		
		List<Hotel> hotels = new ArrayList<>();
		hotels.add(hotel);
		hotels.add(hotel1);

		when(hotelRepository.findAll()).thenReturn(hotels);

		List<Hotel> hotelList = hotelService.getAllHotel();
		Assertions.assertEquals(2, hotelList.size());

		
	}
	
	
	
	@Test
	public void testUpdateHotel() {
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
	    
	    
	    when(hotelRepository.findById(1)).thenReturn(Optional.of(hotel));
	    when(hotelRepository.save(Mockito.any(Hotel.class))).thenReturn(hotel);

	    Hotel updatedHotel = hotelService.updateHotel(hotel);

	    verify(hotelRepository, times(1)).findById(1);
	    verify(hotelRepository, times(1)).save(hotel);


	    Assertions.assertEquals(hotel.getHotelId(), updatedHotel.getHotelId());
	    Assertions.assertEquals(hotel.getHotelName(), updatedHotel.getHotelName());
	    Assertions.assertEquals(hotel.getEmail(), updatedHotel.getEmail());
	    Assertions.assertEquals(hotel.getAddress(), updatedHotel.getAddress());
	    Assertions.assertEquals(hotel.getDescription(), updatedHotel.getDescription());
	    Assertions.assertEquals(hotel.getCity(), updatedHotel.getCity());
	    Assertions.assertEquals(hotel.getAvgRatePerDay(), updatedHotel.getAvgRatePerDay());
	    Assertions.assertEquals(hotel.getPhone1(), updatedHotel.getPhone1());
	    Assertions.assertEquals(hotel.getPhone2(), updatedHotel.getPhone2());
	    Assertions.assertEquals(hotel.getWebsite(), updatedHotel.getWebsite());
		
		
	}

	    @Test
	    public void testCreateRoomDetails() {
	        // Create sample data
	        Hotel hotel = new Hotel();
	        hotel.setHotelId(10);
			RoomDetailsPayload room1=new RoomDetailsPayload();

	        room1.setRoomId(101);
	        room1.setRoomNo("201");
	        room1.setRoomType("Ac");
	        room1.setRatePerDay(3000);
	        room1.setAvailable(true);
	        
	        RoomDetailsPayload room = new RoomDetailsPayload();
	    		room.setRoomId(101);
	    		room.setRoomNo("201");
	    		room.setRoomType("Ac");
	    		room.setRatePerDay(3000);
	    		room.setAvailable(true);
	    		hotel.setHotelId(10);
	    		
	    		
	    		
	    	        when(roomDetailsRepository.createRoomDetails(any(RoomDetailsPayload.class).thenReturn(room);

	    	        RoomDetails createdRoom = roomDetailService.createRoomDetails(room1);

	    	        verify(roomDetailsRepository, times(1)).save(createdRoom);		
	    	        Assertions.assertNotNull(createdRoom);
	    	        Assertions.assertEquals(room.getRoomId(),createdRoom.getRoomId());
	    	        Assertions.assertEquals(room.getRoomType(),createdRoom.getRoomType());
	    		
	    	}
	}