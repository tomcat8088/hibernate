/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2015, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */

package org.hibernate.spatial.dialect.oracle;

import java.util.List;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 * HQL Implementation for the geometry ype function.
 */
class GetGeometryTypeFunction extends SDOObjectMethod {

	GetGeometryTypeFunction() {
		super( "Get_GType", StandardBasicTypes.STRING );
	}

	public String render(Type firstArgumentType, final List args, final SessionFactoryImplementor factory) {
		final StringBuffer buf = new StringBuffer();
		if ( args.isEmpty() ) {
			throw new IllegalArgumentException(
					"First Argument in arglist must be object to which"
							+ " method is applied"
			);
		}

		buf.append( "CASE " ).append( args.get( 0 ) ).append( "." ).append(
				getName()
		).append( "()" );
		buf.append( " WHEN 1 THEN 'POINT'" ).append(
				" WHEN 2 THEN 'LINESTRING'"
		).append(
				" WHEN 3 THEN 'POLYGON'"
		).append(
				" WHEN 5 THEN 'MULTIPOINT'"
		).append(
				" WHEN 6 THEN 'MULTILINE'"
		).append(
				" WHEN 7 THEN 'MULTIPOLYGON'"
		).append( " END" );
		return buf.toString();
	}
}
