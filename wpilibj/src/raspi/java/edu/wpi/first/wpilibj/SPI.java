/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016-2017. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.diozero.api.SpiDevice;

import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;

/**
 * Represents a SPI bus port.
 */
public class SPI extends SensorBase {
	
	private static int devices = 0;
	
	private SpiDevice m_device;
	private int m_bitOrder;
	private int m_clockPolarity;
	private int m_dataOnTrailing;
	
	/**
	 * Constructor.
	 *
	 * @param gpio
	 *            the GPIO channel
	 */
	public SPI(int gpio) {
		devices++;
		
		m_device = new SpiDevice(gpio);
		
		HAL.report(tResourceType.kResourceType_SPI, devices);
	}
	
	/**
	 * Free the resources used by this object.
	 */
	public void free() {
		m_device.close();
	}
	
	/**
	 * Configure the rate of the generated clock signal. The default value is
	 * 500,000 Hz. The maximum value is 4,000,000 Hz.
	 *
	 * @param hz
	 *            The clock rate in Hertz.
	 */
	public final void setClockRate(int hz) {
		throw new UnsupportedOperationException("Cannot set clock rate outside of constructor");
	}
	
	/**
	 * Configure the order that bits are sent and received on the wire to be
	 * most significant bit first.
	 */
	public final void setMSBFirst() {
		m_bitOrder = 1;
		throw new UnsupportedOperationException("Cannot set MSB first outside of constructor");
	}
	
	/**
	 * Configure the order that bits are sent and received on the wire to be
	 * least significant bit first.
	 */
	public final void setLSBFirst() {
		m_bitOrder = 0;
		throw new UnsupportedOperationException("Cannot set LSB first outside of constructor");
	}
	
		
	/**
	 * Write data to the slave device. Blocks until there is space in the output
	 * FIFO.
	 *
	 * <p>
	 * If not running in output only mode, also saves the data received on the
	 * MISO input during the transfer into the receive FIFO.
	 */
	public int write(byte[] dataToSend, int size) {
		ByteBuffer dataToSendBuffer = ByteBuffer.allocateDirect(size);
		dataToSendBuffer.put(dataToSend);
		m_device.write(dataToSendBuffer);
		return 0;
	}
	
	/**
	 * Write data to the slave device. Blocks until there is space in the output
	 * FIFO.
	 *
	 * <p>
	 * If not running in output only mode, also saves the data received on the
	 * MISO input during the transfer into the receive FIFO.
	 *
	 * @param dataToSend
	 *            The buffer containing the data to send. Must be created using
	 *            ByteBuffer.allocateDirect().
	 */
	public int write(ByteBuffer dataToSend, int size) {
		if(!dataToSend.isDirect()) {
			throw new IllegalArgumentException("must be a direct buffer");
		}
		if(dataToSend.capacity() < size) {
			throw new IllegalArgumentException(
					"buffer is too small, must be at least " + size);
		}
		m_device.write(dataToSend);
		return 0;
	}
	
	/**
	 * Read a word from the receive FIFO.
	 *
	 * <p>
	 * Waits for the current transfer to complete if the receive FIFO is empty.
	 *
	 * <p>
	 * If the receive FIFO is empty, there is no active transfer, and initiate
	 * is false, errors.
	 *
	 * @param initiate
	 *            If true, this function pushes "0" into the transmit buffer and
	 *            initiates a transfer. If false, this function assumes that
	 *            data is already in the receive FIFO from a previous write.
	 */
	public int read(boolean initiate, byte[] dataReceived, int size) {
		final int retVal = 0;
		ByteBuffer dataReceivedBuffer;
		ByteBuffer dataToSendBuffer = ByteBuffer.allocateDirect(size);
		if(initiate) {
			dataReceivedBuffer = m_device.writeAndRead(dataToSendBuffer);
		} else {
			dataReceivedBuffer = m_device.writeAndRead(ByteBuffer.allocate(0));
		}
		dataReceivedBuffer.get(dataReceived);
		return retVal;
	}
	
	/**
	 * Read a word from the receive FIFO.
	 *
	 * <p>
	 * Waits for the current transfer to complete if the receive FIFO is empty.
	 *
	 * <p>
	 * If the receive FIFO is empty, there is no active transfer, and initiate
	 * is false, errors.
	 *
	 * @param initiate
	 *            If true, this function pushes "0" into the transmit buffer and
	 *            initiates a transfer. If false, this function assumes that
	 *            data is already in the receive FIFO from a previous write.
	 * @param dataReceived
	 *            The buffer to be filled with the received data. Must be
	 *            created using ByteBuffer.allocateDirect().
	 * @param size
	 *            The length of the transaction, in bytes
	 */
	public int read(boolean initiate, ByteBuffer dataReceived, int size) {
		if(!dataReceived.isDirect()) {
			throw new IllegalArgumentException("must be a direct buffer");
		}
		if(dataReceived.capacity() < size) {
			throw new IllegalArgumentException(
					"buffer is too small, must be at least " + size);
		}
		if(initiate) {
			ByteBuffer dataToSendBuffer = ByteBuffer.allocateDirect(size);
			dataReceived.put(m_device.writeAndRead(dataToSendBuffer));
		}
		dataReceived.put(m_device.writeAndRead(ByteBuffer.allocate(0)));
		return 0;
	}
	
	/**
	 * Perform a simultaneous read/write transaction with the device.
	 *
	 * @param dataToSend
	 *            The data to be written out to the device
	 * @param dataReceived
	 *            Buffer to receive data from the device
	 * @param size
	 *            The length of the transaction, in bytes
	 */
	public int transaction(byte[] dataToSend, byte[] dataReceived, int size) {
		ByteBuffer dataToSendBuffer = ByteBuffer.allocateDirect(size);
		dataToSendBuffer.put(dataToSend);
		ByteBuffer dataReceivedBuffer = ByteBuffer.wrap(dataReceived);
		dataReceivedBuffer.put(m_device.writeAndRead(dataToSendBuffer));
		return 0;
	}
	
	/**
	 * Perform a simultaneous read/write transaction with the device
	 *
	 * @param dataToSend
	 *            The data to be written out to the device. Must be created
	 *            using ByteBuffer.allocateDirect().
	 * @param dataReceived
	 *            Buffer to receive data from the device. Must be created using
	 *            ByteBuffer.allocateDirect().
	 * @param size
	 *            The length of the transaction, in bytes
	 */
	public int transaction(ByteBuffer dataToSend, ByteBuffer dataReceived,
			int size) {
		if(!dataToSend.isDirect()) {
			throw new IllegalArgumentException(
					"dataToSend must be a direct buffer");
		}
		if(dataToSend.capacity() < size) {
			throw new IllegalArgumentException(
					"dataToSend is too small, must be at least " + size);
		}
		if(!dataReceived.isDirect()) {
			throw new IllegalArgumentException(
					"dataReceived must be a direct buffer");
		}
		if(dataReceived.capacity() < size) {
			throw new IllegalArgumentException(
					"dataReceived is too small, must be at least " + size);
		}
		dataReceived.put(m_device.writeAndRead(dataToSend));
		return 0;
	}
}
