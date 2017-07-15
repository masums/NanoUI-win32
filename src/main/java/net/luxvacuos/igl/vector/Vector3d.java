/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.luxvacuos.igl.vector;

import java.io.Serializable;
import java.nio.DoubleBuffer;

/**
 *
 * Holds a 3-tuple vector.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision: 3418 $ $Id: Vector3d.java 3418 2010-09-28 21:11:35Z spasi
 *          $
 */

public class Vector3d extends Vectord implements Serializable, ReadableVector3d, WritableVector3d {

	private static final long serialVersionUID = 1L;

	public double x, y, z;

	/**
	 * Constructor for Vector3d.
	 */
	public Vector3d() {
		super();
	}

	/**
	 * Constructor
	 */
	public Vector3d(ReadableVector3d src) {
		set(src);
	}

	/**
	 * Constructor
	 */
	public Vector3d(double x, double y, double z) {
		set(x, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lwjgl.util.vector.WritableVector2f#set(float, float)
	 */
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lwjgl.util.vector.WritableVector3f#set(float, float, float)
	 */
	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Load from another Vector3d
	 * 
	 * @param src
	 *            The source vector
	 * @return this
	 */
	public Vector3d set(ReadableVector3d src) {
		x = src.getX();
		y = src.getY();
		z = src.getZ();
		return this;
	}

	/**
	 * @return the length squared of the vector
	 */
	public double lengthSquared() {
		return x * x + y * y + z * z;
	}

	/**
	 * Translate a vector
	 * 
	 * @param x
	 *            The translation in x
	 * @param y
	 *            the translation in y
	 * @return this
	 */
	public Vector3d translate(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	/**
	 * Add a vector to another vector and place the result in a destination
	 * vector.
	 * 
	 * @param left
	 *            The LHS vector
	 * @param right
	 *            The RHS vector
	 * @param dest
	 *            The destination vector, or null if a new vector is to be
	 *            created
	 * @return the sum of left and right in dest
	 */
	public static Vector3d add(Vector3d left, Vector3d right, Vector3d dest) {
		if (dest == null)
			return new Vector3d(left.x + right.x, left.y + right.y, left.z + right.z);
		else {
			dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
			return dest;
		}
	}

	/**
	 * Subtract a vector from another vector and place the result in a
	 * destination vector.
	 * 
	 * @param left
	 *            The LHS vector
	 * @param right
	 *            The RHS vector
	 * @param dest
	 *            The destination vector, or null if a new vector is to be
	 *            created
	 * @return left minus right in dest
	 */
	public static Vector3d sub(Vector3d left, Vector3d right, Vector3d dest) {
		if (dest == null)
			return new Vector3d(left.x - right.x, left.y - right.y, left.z - right.z);
		else {
			dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
			return dest;
		}
	}

	/**
	 * The cross product of two vectors.
	 *
	 * @param left
	 *            The LHS vector
	 * @param right
	 *            The RHS vector
	 * @param dest
	 *            The destination result, or null if a new vector is to be
	 *            created
	 * @return left cross right
	 */
	public static Vector3d cross(Vector3d left, Vector3d right, Vector3d dest) {

		if (dest == null)
			dest = new Vector3d();

		dest.set(left.y * right.z - left.z * right.y, right.x * left.z - right.z * left.x,
				left.x * right.y - left.y * right.x);

		return dest;
	}

	/**
	 * Negate a vector
	 * 
	 * @return this
	 */
	public Vectord negate() {
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	/**
	 * Negate a vector and place the result in a destination vector.
	 * 
	 * @param dest
	 *            The destination vector or null if a new vector is to be
	 *            created
	 * @return the negated vector
	 */
	public Vector3d negate(Vector3d dest) {
		if (dest == null)
			dest = new Vector3d();
		dest.x = -x;
		dest.y = -y;
		dest.z = -z;
		return dest;
	}

	/**
	 * Normalise this vector and place the result in another vector.
	 * 
	 * @param dest
	 *            The destination vector, or null if a new vector is to be
	 *            created
	 * @return the normalised vector
	 */
	public Vector3d normalise(Vector3d dest) {
		double l = length();

		if (dest == null)
			dest = new Vector3d(x / l, y / l, z / l);
		else
			dest.set(x / l, y / l, z / l);

		return dest;
	}

	/**
	 * The dot product of two vectors is calculated as v1.x * v2.x + v1.y * v2.y
	 * + v1.z * v2.z
	 * 
	 * @param left
	 *            The LHS vector
	 * @param right
	 *            The RHS vector
	 * @return left dot right
	 */
	public static double dot(Vector3d left, Vector3d right) {
		return left.x * right.x + left.y * right.y + left.z * right.z;
	}

	/**
	 * Calculate the angle between two vectors, in radians
	 * 
	 * @param a
	 *            A vector
	 * @param b
	 *            The other vector
	 * @return the angle between the two vectors, in radians
	 */
	public static double angle(Vector3d a, Vector3d b) {
		double dls = dot(a, b) / (a.length() * b.length());
		if (dls < -1f)
			dls = -1f;
		else if (dls > 1.0f)
			dls = 1.0f;
		return Math.acos(dls);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lwjgl.vector.Vector#load(FloatBuffer)
	 */
	public Vectord load(DoubleBuffer buf) {
		x = buf.get();
		y = buf.get();
		z = buf.get();
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lwjgl.vector.Vector#scale(float)
	 */
	public Vectord scale(double scale) {

		x *= scale;
		y *= scale;
		z *= scale;

		return this;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lwjgl.vector.Vector#store(FloatBuffer)
	 */
	public Vectord store(DoubleBuffer buf) {

		buf.put(x);
		buf.put(y);
		buf.put(z);

		return this;
	}

	public Vector3d div(double scalar) {
		x /= scalar;
		y /= scalar;
		z /= scalar;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder(64);

		sb.append("Vector3d[");
		sb.append(x);
		sb.append(", ");
		sb.append(y);
		sb.append(", ");
		sb.append(z);
		sb.append(']');
		return sb.toString();
	}

	/**
	 * @return x
	 */
	public final double getX() {
		return x;
	}

	/**
	 * @return y
	 */
	public final double getY() {
		return y;
	}

	/**
	 * Set X
	 * 
	 * @param x
	 */
	public final void setX(double x) {
		this.x = x;
	}

	/**
	 * Set Y
	 * 
	 * @param y
	 */
	public final void setY(double y) {
		this.y = y;
	}

	/**
	 * Set Z
	 * 
	 * @param z
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/*
	 * (Overrides)
	 * 
	 * @see org.lwjgl.vector.ReadableVector3f#getZ()
	 */
	public double getZ() {
		return z;
	}

}
