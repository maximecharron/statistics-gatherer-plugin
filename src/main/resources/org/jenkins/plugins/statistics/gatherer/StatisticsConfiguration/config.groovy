package org.jenkins.plugins.statistics.gatherer.StatisticsConfiguration

def f=namespace(lib.FormTagLib)

f.section(title:_("Statistics Gatherer")) {
    f.entry(title:_("Queue URL"), field:"queueUrl") {
        f.textbox(default: "http://ci.mycompany.com/api/queue")
    }
    f.entry(title:_("Build URL"), field:"buildUrl") {
        f.textbox(default: "http://ci.mycompany.com/api/build")
    }
    f.entry(title:_("Project URL"), field:"projectUrl") {
        f.textbox(default: "http://ci.mycompany.com/api/projects")
    }
    f.entry(title:_("BuildSteps URL"), field:"buildStepUrl") {
        f.textbox(default: "http://ci.mycompany.com/api//step")
    }
    f.entry(title:_("ScmCheckoutInfo URL"), field:"scmCheckoutUrl") {
        f.textbox(default: "http://ci.mycompany.com/api//scm")
    }

    f.entry(title:_("Send Queue Info"), field:"queueInfo") {
        f.checkbox(default: true)
    }
    f.entry(title:_("Send Build Info"), field:"buildInfo") {
        f.checkbox(default: true)
    }
    f.entry(title:_("Send Job Info"), field:"projectInfo") {
        f.checkbox(default: true)
    }
    f.entry(title:_("Send BuildSteps Info"), field:"buildStepInfo") {
        f.checkbox(default: true)
    }
    f.entry(title:_("Send Scm Checkout Info"), field:"scmCheckoutInfo") {
        f.checkbox(default: true)
    }
}
