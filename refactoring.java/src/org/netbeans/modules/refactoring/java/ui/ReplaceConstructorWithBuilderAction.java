/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.refactoring.java.ui;

import org.netbeans.modules.refactoring.java.ui.ContextAnalyzer;
import org.netbeans.modules.refactoring.java.ui.JavaRefactoringGlobalAction;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 * @author Jan Becicka
 */
@ActionID(id = "org.netbeans.modules.java.hints.jackpot.refactoring.ReplaceConstructorWithBuilderAction", category = "Refactoring")
@ActionRegistration(displayName = "#LBL_ReplaceConstructorWithBuilderAction")
@ActionReferences({
    @ActionReference(path = "Editors/text/x-java/RefactoringActions" , name = "ReplaceConstructorWithBuilderAction", position = 1825),
    @ActionReference(path = "Shortcuts", name = "CS-Y B")
})
public final class ReplaceConstructorWithBuilderAction extends JavaRefactoringGlobalAction {

    public ReplaceConstructorWithBuilderAction() {
        super(NbBundle.getMessage(ReplaceConstructorWithFactoryAction.class, "LBL_ReplaceConstructorWithBuilderAction"), null); // NOI18N
        putValue("noIconInMenu", Boolean.TRUE); // NOI18N
    }

    public org.openide.util.HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    protected boolean asynchronous() {
        return false;
    }

    protected boolean enable(Lookup context) {
        return ContextAnalyzer.canRefactorSingle(context, true, false);
    }

    @Override
    public void performAction(Lookup context) {
        Runnable task = ContextAnalyzer.createTask(context, ReplaceConstructorWithBuilderUI.factory());
        UIUtilities.runWhenScanFinished(task, getName());
    }
}
