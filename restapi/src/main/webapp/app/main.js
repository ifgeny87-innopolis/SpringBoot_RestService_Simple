/**
 * Скрипт позволяет тестировать Restful API приложения
 */

let prefix = '/api';

// run
$(x => {
    scenario1();
});

/**
 * Scenario 1
 * Проверка авторизации и выхода
 */
let scenario1 = function (cb) {
    /* Объект будет хранить состояние */
    this.credentials = {};

    /* Когда получен токен, его надо сохранить */
    this.setToken = function (isFail, res) {
        let token = res.data.token;
        if (!token || !token.trim()) {
            throw new Error('Method must returns token!');
        }
        this.credentials.token = token;
    };

    /* Нужен для проверки результата */
    this.checkExit = function (isFail) {
        return true;
    };

    /* Создаю и запускаю сценарий */
    createTask('Scenario 1', true, cb)
        .add(METH.accountEnter.bind(this, 'test', '123'), '', this.setToken)
        .add(METH.accountExit.bind(this, this.credentials), 'exit')
        .add(METH.accountExit.bind(this, this.credentials), 'exit again', this.checkExit)
        .start();
};

/**
 * Main Test Decorator
 * @param scenarioName  Название теста
 * @param logging       Должен ли тест выводить логи
 * @param cb            Метод, который будет вызван по завршению теста
 * @returns {createTask}
 */
let createTask = function (_scenarioName, _logging = true, cb = null) {
    this.scenarioName = ('Task ' + (_scenarioName ? '`' + _scenarioName + '`' : '')).trim();
    this.logging = _logging;
    this.callback = cb;

    /* Стек методов */
    this.stack = [];

    /* Время начала теста */
    this.startDate = 0;

    /**
     * Логирует работы теста
     * @param args
     */
    this.log = function (...args) {
        if (this.logging) {
            console.info.apply(this, args);
        }
    };

    /**
     * Метод добавляет один шаг в стек выполнения
     * @param method    Метод выполнения
     * @param stepTitle Название шага
     * @param cb        Callback
     * @returns {createTask}
     */
    this.add = function (method, stepTitle = null, cb = null) {
        stack.push({index: stack.length + 1, method: method, title: stepTitle, callback: cb});
        return this;
    };

    /**
     * Выполняет запуск теста
     */
    this.start = function () {
        this.log('%s started (%d steps)', scenarioName, stack.length);
        this.startDate = +Date.now();
        this.nextStep();
    };

    /**
     * Запуск следующего шага
     */
    this.nextStep = function () {
        if (stack.length > 0) {
            (i = stack.shift()).method(
                // success callback
                this.finishHandler(i).bind(this),
                // fail callback
                this.finishHandler(i, true).bind(this));
        }
        else {
            finish();
        }
    };

    /**
     * Декоратор - хук на удачно завершенный тест
     * @param methObj
     * @returns {Function}
     */
    this.finishHandler = function (methObj, isFailTestDecor = false) {
        let title = (methObj.index + ') ' + (methObj.title ? '`' + methObj.title + '`' : "")).trim();
        this.log('step %s starts', title);
        let startDate = +Date.now();

        return function (...args) {
            let length = +Date.now() - startDate;
            let sayResult = false;

            if (methObj.callback) {
                // если метод захочет, продолжим выполнение
                sayResult = methObj.callback.call(this, isFailTestDecor, ...args);
            }

            // возможно, случилась ошибка при выполнении шага
            // но метод проверки выше мог сказать, что все ок
            // проверим это
            if (isFailTestDecor && !sayResult) {
                // все говорит о том, что шаг завершился неудачей
                this.log('step %s fails in %s ms', title, length, args);
                this.fail();
            }

            this.log('step %s finished in %s ms', title, length, args);
            this.nextStep();
        }
    };

    /**
     * Метод вызывается когда стек выполнения заканчивается
     */
    this.finish = function () {
        let length = +Date.now() - this.startDate;
        this.log('%s finished in %f ms', scenarioName, length);
        if (this.callback) {
            this.callback();
        }
    };

    /**
     * Метод вызывается когда произошла ошибка при выполнении теста
     */
    this.fail = function () {
        let length = +Date.now() - this.startDate;
        console.error('%s failed in %f ms', scenarioName, length);
        if (this.callback) {
            this.callback(true);
        }
    };

    return this;
};

/**
 * Методы для тестирования
 */
let METH = {
    errorHandler(xhr) {
        console.error('Error #%s%s',
            xhr.status,
            xhr.responseJSON ? ': ' + xhr.responseJSON.message : '',
            xhr.responseJSON || xhr.responseText,
            xhr);
    },

    accountEnter(username, password, success, fail) {
        let obj = {username, password};
        $.post(prefix + '/account/enter', obj, success, 'json')
            .fail(fail || METH.errorHandler);
    },

    accountExit({token}, success, fail) {
        let obj = {token};
        $.get(prefix + '/account/exit', obj, success, 'json')
            .fail(fail || METH.errorHandler);
    },
};