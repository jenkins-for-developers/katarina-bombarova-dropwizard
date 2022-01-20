import hudson.model.*
Build build = Executor.currentExecutor().currentExecutable
def params = build.getAction(ParametersAction).parameters

def prefix   = params.find { it.name == 'VE_SLOZCE_JMENEM' }?.value
def vytvorit = params.find { it.name == 'VYTVORIT_JOBY' }?.value


def projekty = ['alfa', 'beta', 'gamma']


if (vytvorit)
{
    if (prefix)
    {
        folder(prefix) {
            description('Vsechno tohle je generovane')
            views {
                listView('Vsechno') {
                    description('Vsechny veci uvnitr')
                    jobFilters {
                        all()
                    }
                    recurse(true)
                    columns {
                        status()
                        weather()
                        name()
                        lastSuccess()
                        lastFailure()
                        lastDuration()
                        buildButton()
                        // configureProject()
                    }
                }
                buildMonitorView('Monitor') {
                    jobFilters {
                        all()
                    }
                    recurse(true)
                }
            }
            primaryView('Vsechno')
        }


        for (projekt in projekty) {
            folder("${prefix}/${projekt}")

            ['develop', 'feature', 'PR', 'bugfix', 'master'].each {
                job("${prefix}/${projekt}/${it}") {
                    steps {
                        shell {
                            command('#!/usr/bin/env bash\n sleep 1; exit $((RANDOM % 3))')
                            unstableReturn(1)
                        }
                    }
                }
            }
        }
    }
    else
    {
        for (projekt in projekty) {
            folder(projekt)

            ['develop', 'feature', 'PR', 'bugfix', 'master'].each {
                job("${prefix}/${projekt}/${it}")
            }
        }
    }
}
