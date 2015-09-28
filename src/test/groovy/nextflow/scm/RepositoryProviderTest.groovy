/*
 * Copyright (c) 2013-2015, Centre for Genomic Regulation (CRG).
 * Copyright (c) 2013-2015, Paolo Di Tommaso and the respective authors.
 *
 *   This file is part of 'Nextflow'.
 *
 *   Nextflow is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Nextflow is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Nextflow.  If not, see <http://www.gnu.org/licenses/>.
 */

package nextflow.scm

import spock.lang.Specification

/**
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
class RepositoryProviderTest extends Specification {

    def 'should create repository provider object' () {

        def provider

        when:
        provider = RepositoryProvider.create(new ProviderConfig('github'),'project/x')
        then:
        provider instanceof GithubRepositoryProvider
        provider.repoUrl == 'https://api.github.com/repos/project/x'

        when:
        provider = RepositoryProvider.create(new ProviderConfig('gitlab'),'project/y')
        then:
        provider instanceof GitlabRepositoryProvider
        provider.repoUrl == 'https://gitlab.com/api/v3/projects/project%2Fy'

        when:
        provider = RepositoryProvider.create(new ProviderConfig('bitbucket'),'project/z')
        then:
        provider instanceof BitbucketRepositoryProvider
        provider.repoUrl == 'https://bitbucket.org/api/2.0/repositories/project/z'

        when:
        provider = RepositoryProvider.create(new ProviderConfig('local', [path:'/user/data']),'project/w')
        then:
        provider.repoUrl == 'file:/user/data/project/w'
    }
}
