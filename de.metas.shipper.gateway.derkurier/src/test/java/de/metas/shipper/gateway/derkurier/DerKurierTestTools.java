package de.metas.shipper.gateway.derkurier;

import static de.metas.shipper.gateway.derkurier.DerKurierConstants.SHIPPER_GATEWAY_ID;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import com.google.common.collect.ImmutableList;

import de.metas.shipper.gateway.derkurier.DerKurierDeliveryData.DerKurierDeliveryDataBuilder;
import de.metas.shipper.gateway.derkurier.misc.DerKurierServiceType;
import de.metas.shipper.gateway.derkurier.restapi.models.RequestParticipant;
import de.metas.shipper.gateway.derkurier.restapi.models.RequestParticipant.RequestParticipantBuilder;
import de.metas.shipper.gateway.derkurier.restapi.models.RoutingRequest;
import de.metas.shipper.gateway.spi.model.Address;
import de.metas.shipper.gateway.spi.model.ContactPerson;
import de.metas.shipper.gateway.spi.model.CountryCode;
import de.metas.shipper.gateway.spi.model.DeliveryDate;
import de.metas.shipper.gateway.spi.model.DeliveryOrder;
import de.metas.shipper.gateway.spi.model.DeliveryOrder.DeliveryOrderBuilder;
import de.metas.shipper.gateway.spi.model.DeliveryPosition;
import de.metas.shipper.gateway.spi.model.OrderId;
import de.metas.shipper.gateway.spi.model.PickupDate;

/*
 * #%L
 * de.metas.shipper.gateway.derkurier
 * %%
 * Copyright (C) 2018 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

public class DerKurierTestTools
{
	public static final int M_SHIPPER_ID = 50;
	public static final int M_SHIPPER_TRANSPORTATION_ID = 60;

	private static final CountryCode COUNTRY_CODE_DE = CountryCode.builder().alpha2("DE").alpha3("DEU").build();

	public static DeliveryOrder createTestDeliveryOrderwithOneLine()
	{
		final DeliveryOrderBuilder deliveryOrderBuilder = prepareDeliveryOrderBuilder();
		final DerKurierDeliveryDataBuilder derKurierDeliveryDataBuilder = prepatreDerKurierDeliveryDataBuilder();

		final DeliveryOrder deliveryOrder = deliveryOrderBuilder
				.shipperId(M_SHIPPER_ID)
				.shipperTransportationId(M_SHIPPER_TRANSPORTATION_ID)
				.deliveryPosition(DeliveryPosition.builder()
						.numberOfPackages(5)
						.packageIds(ImmutableList.of(1, 2, 3, 4, 5))
						.grossWeightKg(1)
						.customDeliveryData(derKurierDeliveryDataBuilder.parcelNumber("parcelnumber1").build())
						.build())
				.build();
		return deliveryOrder;
	}

	public static DeliveryOrder createTestDeliveryOrder()
	{
		final DeliveryOrderBuilder deliveryOrderBuilder = prepareDeliveryOrderBuilder();
		final DerKurierDeliveryDataBuilder derKurierDeliveryDataBuilder = prepatreDerKurierDeliveryDataBuilder();

		final DeliveryOrder deliveryOrder = deliveryOrderBuilder
				.deliveryPosition(DeliveryPosition.builder()
						.numberOfPackages(5)
						.packageIds(ImmutableList.of(1, 2, 3, 4, 5))
						.grossWeightKg(1)
						.customDeliveryData(derKurierDeliveryDataBuilder.parcelNumber("parcelnumber1").build())
						.build())
				.deliveryPosition(DeliveryPosition.builder()
						.numberOfPackages(1)
						.packageIds(ImmutableList.of(6, 7))
						.grossWeightKg(2)
						.customDeliveryData(derKurierDeliveryDataBuilder.parcelNumber("parcelnumber2").build())
						.build())
				.build();
		return deliveryOrder;
	}

	public static DerKurierDeliveryDataBuilder prepatreDerKurierDeliveryDataBuilder()
	{
		final DerKurierDeliveryDataBuilder derKurierDeliveryDataBuilder = DerKurierDeliveryData
				.builder()
				.customerNumber("customerNumber-12345")
				.station("030")
				.desiredTimeFrom(LocalTime.of(9, 0))
				.desiredTimeTo(LocalTime.of(17, 30));

		return derKurierDeliveryDataBuilder;
	}

	public static DeliveryOrderBuilder prepareDeliveryOrderBuilder()
	{
		final DeliveryOrderBuilder deliveryOrderBuilder = DeliveryOrder.builder()
				.orderId(OrderId.of(SHIPPER_GATEWAY_ID, "1234"))
				.serviceType(DerKurierServiceType.OVERNIGHT)
				.pickupAddress(createPickupAddress())
				.pickupDate(createPickupDate())
				.deliveryDate(DeliveryDate.builder()
						.date(LocalDate.of(2018, Month.JANUARY, 9))
						.timeFrom(LocalTime.of(9, 0))
						.timeTo(LocalTime.of(17, 30))
						.build())
				.deliveryAddress(Address.builder()
						.companyName1("to company")
						.companyDepartment("leer")
						.street1("street 1")
						.street2("street 2")
						.houseNo("1")
						.zipCode("54321")
						.city("Köln")
						.country(COUNTRY_CODE_DE)
						.build())
				.deliveryContact(ContactPerson.builder()
						.emailAddress("test@testmail.org")
						.build())
				.customerReference("some info for customer");
		return deliveryOrderBuilder;
	}

	public static PickupDate createPickupDate()
	{
		return PickupDate.builder()
				.date(LocalDate.of(2018, Month.JANUARY, 8))
				.build();
	}

	public static Address createPickupAddress()
	{
		return Address.builder()
				.companyName1("pickupAddress-companyName1")
				.street1("street 1")
				.houseNo("1")
				.zipCode("12345")
				.city("Bonn")
				.country(COUNTRY_CODE_DE)
				.build();
	}

	public static RoutingRequest createRoutingRequest_times_with_seconds()
	{
		final RequestParticipant sender = prepareRequestParticipantBuilder()
				.build();

		final RoutingRequest routingRequest = RoutingRequest.builder()
				.sendDate(LocalDate.now())
				.sender(sender)
				.build();
		return routingRequest;
	}

	/**
	 * DerKurier doesn't care for seconds it its times, so for some test cases this method creates the better test-requests.
	 *
	 * @return
	 */
	public static RoutingRequest createRoutingRequest_times_without_seconds()
	{
		final RequestParticipant sender = prepareRequestParticipantBuilder()
				.timeFrom(LocalTime.of(10, 20, 0, 0))
				.timeTo(LocalTime.of(11, 21, 0, 0))
				.build();

		final RoutingRequest routingRequest = RoutingRequest.builder()
				.sendDate(LocalDate.now())
				.sender(sender)
				.build();
		return routingRequest;
	}

	private static RequestParticipantBuilder prepareRequestParticipantBuilder()
	{
		return RequestParticipant.builder()
				.country("DE")
				.zip("50969")
				.timeFrom(LocalTime.of(10, 20, 30, 40)) // here we provide localtime up to the nano-second, but the web-service only wants hour:minute
				.timeTo(LocalTime.of(11, 21, 31, 41));
	}
}
