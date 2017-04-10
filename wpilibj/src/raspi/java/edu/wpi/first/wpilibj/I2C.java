/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2017. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import java.nio.ByteBuffer;

import com.diozero.api.I2CDevice;

import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.util.BoundaryException;

/**
 * I2C bus interface class.
 *
 * <p>
 * This class is intended to be used by sensor (and other I2C device) drivers.
 * It probably should not be used directly.
 */
public class I2C extends SensorBase {
	
	private final I2CDevice m_device;
	
	/**
	 * Constructor.
	 *
	 * @param port
	 *            The I2C port the device is connected to.
	 * @param deviceAddress
	 *            The address of the device on the I2C bus.
	 */
	public I2C(int controller, int address, int addressSize, int clockFrequency) {
		
		m_device = new I2CDevice(controller, address, addressSize, clockFrequency);
		HAL.report(tResourceType.kResourceType_I2C, address);
	}
	
	/**
	 * Destructor.
	 */
	public void free() {
		m_device.close();
	}
	
	/**
	 * Execute a write transaction with the device.
	 *
	 * <p>
	 * Write a single byte to a register on a device and wait until the
	 * transaction is complete.
	 *
	 * @param registerAddress
	 *            The address of the register on the device to be written.
	 * @param data
	 *            The byte to write to the register on the device.
	 * @return Transfer Aborted... false for success, true for aborted.
	 */
	public synchronized boolean write(int registerAddress, int data) {
		m_device.writeByte(registerAddress, data);
		return true;
	}
	
	/**
	 * Execute a write transaction with the device.
	 *
	 * <p>
	 * Write multiple bytes to a register on a device and wait until the
	 * transaction is complete.
	 *
	 * @param data
	 *            The data to write to the device.
	 * @return Transfer Aborted... false for success, true for aborted.
	 */
	public synchronized boolean writeBulk(byte[] data) {
		m_device.write(data);
		return true;
	}
	
	/**
	 * Execute a write transaction with the device.
	 *
	 * <p>
	 * Write multiple bytes to a register on a device and wait until the
	 * transaction is complete.
	 *
	 * @param data
	 *            The data to write to the device. Must be created using
	 *            ByteBuffer.allocateDirect().
	 * @return Transfer Aborted... false for success, true for aborted.
	 */
	public synchronized boolean writeBulk(ByteBuffer data, int size) {
		if(!data.isDirect()) {
			throw new IllegalArgumentException("must be a direct buffer");
		}
		if(data.capacity() < size) {
			throw new IllegalArgumentException(
					"buffer is too small, must be at least " + size);
		}
		m_device.write(data.array());
		return true;
	}
	
	/**
	 * Execute a read transaction with the device.
	 *
	 * <p>
	 * Read bytes from a device. Most I2C devices will auto-increment the
	 * register pointer internally allowing you to read consecutive registers on
	 * a device in a single transaction.
	 *
	 * @param registerAddress
	 *            The register to read first in the transaction.
	 * @param count
	 *            The number of bytes to read in the transaction.
	 * @param buffer
	 *            A pointer to the array of bytes to store the data read from
	 *            the device.
	 * @return Transfer Aborted... false for success, true for aborted.
	 */
	public boolean read(int registerAddress, int count, byte[] buffer) {
		if(count < 1) {
			throw new BoundaryException("Value must be at least 1, " + count
					+ " given");
		}
		
		if(buffer == null) {
			throw new NullPointerException("Null return buffer was given");
		}
		
		m_device.read(registerAddress, count, ByteBuffer.wrap(buffer));
		return true;
	}
	
	/**
	 * Execute a read transaction with the device.
	 *
	 * <p>
	 * Read bytes from a device. Most I2C devices will auto-increment the
	 * register pointer internally allowing you to read consecutive registers on
	 * a device in a single transaction.
	 *
	 * @param registerAddress
	 *            The register to read first in the transaction.
	 * @param count
	 *            The number of bytes to read in the transaction.
	 * @param buffer
	 *            A buffer to store the data read from the device. Must be
	 *            created using ByteBuffer.allocateDirect().
	 * @return Transfer Aborted... false for success, true for aborted.
	 */
	public boolean read(int registerAddress, int count, ByteBuffer buffer) {
		if(count < 1) {
			throw new BoundaryException("Value must be at least 1, " + count
					+ " given");
		}
		
		if(!buffer.isDirect()) {
			throw new IllegalArgumentException("must be a direct buffer");
		}
		if(buffer.capacity() < count) {
			throw new IllegalArgumentException(
					"buffer is too small, must be at least " + count);
		}
		m_device.read(registerAddress, count, buffer);
		return true;
	}
	
	/**
	 * Execute a read only transaction with the device.
	 *
	 * <p>
	 * Read bytes from a device. This method does not write any data to prompt
	 * the device.
	 *
	 * @param buffer
	 *            A pointer to the array of bytes to store the data read from
	 *            the device.
	 * @param count
	 *            The number of bytes to read in the transaction.
	 * @return Transfer Aborted... false for success, true for aborted.
	 */
	public boolean readOnly(byte[] buffer, int count) {
		if(count < 1) {
			throw new BoundaryException("Value must be at least 1, " + count
					+ " given");
		}
		
		if(buffer == null) {
			throw new NullPointerException("Null return buffer was given");
		}
		
		m_device.read(ByteBuffer.wrap(buffer));
		return true;
	}
	
	/**
	 * Execute a read only transaction with the device.
	 *
	 * <p>
	 * Read bytes from a device. This method does not write any data to prompt
	 * the device.
	 *
	 * @param buffer
	 *            A pointer to the array of bytes to store the data read from
	 *            the device. Must be created using ByteBuffer.allocateDirect().
	 * @param count
	 *            The number of bytes to read in the transaction.
	 * @return Transfer Aborted... false for success, true for aborted.
	 */
	public boolean readOnly(ByteBuffer buffer, int count) {
		if(count < 1) {
			throw new BoundaryException("Value must be at least 1, " + count
					+ " given");
		}
		
		if(!buffer.isDirect()) {
			throw new IllegalArgumentException("must be a direct buffer");
		}
		if(buffer.capacity() < count) {
			throw new IllegalArgumentException(
					"buffer is too small, must be at least " + count);
		}
		m_device.read(buffer);
		return true;
	}
}
