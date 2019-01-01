package org.koin.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.fail
import org.junit.Test
import org.koin.Simple
import org.koin.core.error.ClosedScopeException
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class ScopedDefinitionTest {

    @Test
    fun `get scoped definition from a scope`() {
        val koin = koinApplication {
            modules(
                module {
                    scoped { Simple.ComponentA() }
                }
            )
        }.koin

        val scope = koin.createScope("myScope")

        assertEquals(scope.get<Simple.ComponentA>(), scope.get<Simple.ComponentA>())
    }

    @Test
    fun `get different instances from different scopes`() {
        val koin = koinApplication {
            modules(
                module {
                    scoped { Simple.ComponentA() }
                }
            )
        }.koin

        val scope1 = koin.createScope("myScope1")
        val scope2 = koin.createScope("myScope2")

        assertNotEquals(scope1.get<Simple.ComponentA>(), scope2.get<Simple.ComponentA>())
    }

    @Test
    fun `close a scope and drop instances`() {
        val koin = koinApplication {
            modules(
                module {
                    scoped { Simple.ComponentA() }
                }
            )
        }.koin

        val scope = koin.createScope("myScope")
        scope.get<Simple.ComponentA>()
        scope.close()

        try {
            scope.get<Simple.ComponentA>()
            fail()
        } catch (e: ClosedScopeException) {
            e.printStackTrace()
        }
    }
}