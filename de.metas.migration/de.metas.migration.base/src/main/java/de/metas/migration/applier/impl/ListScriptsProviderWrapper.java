package de.metas.migration.applier.impl;

/*
 * #%L
 * de.metas.migration.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.util.Iterator;
import java.util.List;

import de.metas.migration.IScript;
import de.metas.migration.applier.IScriptsProvider;

/**
 * Wraps a a list of {@link IScript}s to {@link IScriptsProvider}
 *
 * @author tsa
 *
 */
public class ListScriptsProviderWrapper implements IScriptsProvider
{
	private final List<IScript> scripts;

	public ListScriptsProviderWrapper(final List<IScript> scripts)
	{
		this.scripts = scripts;
	}

	@Override
	public String toString()
	{
		return "ListScriptsProvider [scripts=" + scripts + "]";
	}

	@Override
	public Iterator<IScript> getScripts()
	{
		return scripts.iterator();
	}
}
