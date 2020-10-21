import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDoubleEntryLoggerMySuffix, defaultValue } from 'app/shared/model/double-entry-logger-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_DOUBLEENTRYLOGGER_LIST: 'doubleEntryLogger/FETCH_DOUBLEENTRYLOGGER_LIST',
  FETCH_DOUBLEENTRYLOGGER: 'doubleEntryLogger/FETCH_DOUBLEENTRYLOGGER',
  CREATE_DOUBLEENTRYLOGGER: 'doubleEntryLogger/CREATE_DOUBLEENTRYLOGGER',
  UPDATE_DOUBLEENTRYLOGGER: 'doubleEntryLogger/UPDATE_DOUBLEENTRYLOGGER',
  DELETE_DOUBLEENTRYLOGGER: 'doubleEntryLogger/DELETE_DOUBLEENTRYLOGGER',
  RESET: 'doubleEntryLogger/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDoubleEntryLoggerMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type DoubleEntryLoggerMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: DoubleEntryLoggerMySuffixState = initialState, action): DoubleEntryLoggerMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DOUBLEENTRYLOGGER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DOUBLEENTRYLOGGER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DOUBLEENTRYLOGGER):
    case REQUEST(ACTION_TYPES.UPDATE_DOUBLEENTRYLOGGER):
    case REQUEST(ACTION_TYPES.DELETE_DOUBLEENTRYLOGGER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DOUBLEENTRYLOGGER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DOUBLEENTRYLOGGER):
    case FAILURE(ACTION_TYPES.CREATE_DOUBLEENTRYLOGGER):
    case FAILURE(ACTION_TYPES.UPDATE_DOUBLEENTRYLOGGER):
    case FAILURE(ACTION_TYPES.DELETE_DOUBLEENTRYLOGGER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DOUBLEENTRYLOGGER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DOUBLEENTRYLOGGER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DOUBLEENTRYLOGGER):
    case SUCCESS(ACTION_TYPES.UPDATE_DOUBLEENTRYLOGGER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DOUBLEENTRYLOGGER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/double-entry-loggers';

// Actions

export const getEntities: ICrudGetAllAction<IDoubleEntryLoggerMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DOUBLEENTRYLOGGER_LIST,
  payload: axios.get<IDoubleEntryLoggerMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IDoubleEntryLoggerMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DOUBLEENTRYLOGGER,
    payload: axios.get<IDoubleEntryLoggerMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDoubleEntryLoggerMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DOUBLEENTRYLOGGER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDoubleEntryLoggerMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DOUBLEENTRYLOGGER,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDoubleEntryLoggerMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DOUBLEENTRYLOGGER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
